package com.sk.outlay.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sk.outlay.data.OutlayDatabase
import com.sk.outlay.data.Repository
import com.sk.outlay.data.RepositoryImpl
import com.sk.outlay.data.converters.LocalDateTimeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Named
import javax.inject.Singleton

const val USER_PREFERENCES_NAME = "com_sk_goals_app"

val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

object PreferencesKeys {
    val SETUP_FINISHED_KEY = booleanPreferencesKey("has_finished_setup")
}

class AppDataStore(dataStore: DataStore<Preferences>) {

}

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @Named(APP_COROUTINE_SCOPE_QUALIFIER) externalScope: CoroutineScope,
        @Named(IO_DISPATCHER_QUALIFIER) dispatcher: CoroutineDispatcher,
        dateTimeConverter: LocalDateTimeConverter
    ) = OutlayDatabase.getInstance(context, externalScope, dispatcher, dateTimeConverter)

    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext context: Context) = context.dataStore

    @Provides
    fun provideDateTimeConverter(
        @Named(DB_DATE_FORMAT_QUALIFIER) dbDateFormatter: DateTimeFormatter,
        @Named(DB_TIME_FORMAT_QUALIFIER) dbTimeFormatter: DateTimeFormatter,
        @Named(DB_DATE_TIME_FORMAT_QUALIFIER) dbDateTimeFormatter: DateTimeFormatter
    ): LocalDateTimeConverter =
        LocalDateTimeConverter(dbDateFormatter, dbTimeFormatter, dbDateTimeFormatter)

    @Provides
    @Singleton
    fun provideRepository(database: OutlayDatabase): Repository {
        return RepositoryImpl(database)
    }
}