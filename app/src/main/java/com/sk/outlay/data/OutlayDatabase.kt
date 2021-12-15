package com.sk.outlay.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sk.outlay.data.converters.LocalDateTimeConverter
import com.sk.outlay.data.converters.UuidRoomTypeConverter
import com.sk.outlay.data.dao.*
import com.sk.outlay.data.entities.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Account::class,
        Category::class,
        Cycle::class,
        Transaction::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    UuidRoomTypeConverter::class,
    LocalDateTimeConverter::class,
)
abstract class OutlayDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun cycleDao(): CycleDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var instance: OutlayDatabase? = null

        fun getInstance(
            context: Context,
            externalScope: CoroutineScope,
            dispatcher: CoroutineDispatcher,
            dateTimeConverter: LocalDateTimeConverter
        ): OutlayDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(
                    context,
                    externalScope,
                    dispatcher,
                    dateTimeConverter
                ).also { newInstance -> instance = newInstance }
            }
        }

        private fun buildDatabase(
            context: Context,
            externalScope: CoroutineScope,
            dispatcher: CoroutineDispatcher,
            dateTimeConverter: LocalDateTimeConverter
        ): OutlayDatabase {
            return Room.databaseBuilder(context, OutlayDatabase::class.java, "outlay_database")
                .addTypeConverter(dateTimeConverter)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val appDb =
                            getInstance(context, externalScope, dispatcher, dateTimeConverter)
                        externalScope.launch(dispatcher) {
                            seedData(appDb)
                        }
                    }
                })
                .build()
        }

        suspend fun seedData(db: OutlayDatabase) {
            with(db) {
                cycleDao().insert(SeedData.cycle)
                accountDao().insert(SeedData.accounts)
                categoryDao().insert(SeedData.categories)
                transactionDao().insert(SeedData.transaction)
            }
        }
    }
}