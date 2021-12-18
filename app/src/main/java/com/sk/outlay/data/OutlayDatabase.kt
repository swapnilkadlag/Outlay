package com.sk.outlay.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sk.outlay.data.converters.LocalDateTimeConverter
import com.sk.outlay.data.converters.UuidRoomTypeConverter
import com.sk.outlay.data.dao.AccountDao
import com.sk.outlay.data.dao.BudgetDao
import com.sk.outlay.data.dao.CategoryDao
import com.sk.outlay.data.dao.TransactionDao
import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.entities.Budget
import com.sk.outlay.data.entities.Category
import com.sk.outlay.data.entities.Transaction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        Account::class,
        Budget::class,
        Category::class,
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
    abstract fun budgetDao(): BudgetDao
    abstract fun categoryDao(): CategoryDao
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
                accountDao().insert(SeedData.accounts)
                budgetDao().insert(SeedData.budget)
                categoryDao().insert(SeedData.categories)
                transactionDao().insert(SeedData.transaction)
            }
        }
    }
}