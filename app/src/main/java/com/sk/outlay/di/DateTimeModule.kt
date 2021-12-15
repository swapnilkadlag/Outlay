package com.sk.outlay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Named
import javax.inject.Qualifier

const val DB_TIME_FORMAT = "HH:mm:ss.SSS"
const val DB_DATE_FORMAT = "yyyy-MM-dd"
const val DB_DATE_TIME_FORMAT = "$DB_DATE_FORMAT $DB_TIME_FORMAT"

const val DISPLAY_TIME_FORMAT = "HH:mm a"
const val DISPLAY_DATE_FORMAT = "E dd, yyyy"
const val DISPLAY_DATE_TIME_FORMAT = "$DISPLAY_DATE_FORMAT $DISPLAY_TIME_FORMAT"

const val DB_TIME_FORMAT_QUALIFIER = "DbTimeFormat"
const val DB_DATE_FORMAT_QUALIFIER = "DbDateFormat"
const val DB_DATE_TIME_FORMAT_QUALIFIER = "DbDateTimeFormat"

const val DISPLAY_TIME_FORMAT_QUALIFIER = "DisplayTimeFormat"
const val DISPLAY_DATE_FORMAT_QUALIFIER = "DisplayDateFormat"
const val DISPLAY_DATE_TIME_FORMAT_QUALIFIER = "DisplayDateTimeFormat"


@Module
@InstallIn(SingletonComponent::class)
class DateTimeModule {
    @Provides
    @Named(DB_DATE_FORMAT_QUALIFIER)
    fun provideDbDateFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(DB_DATE_FORMAT)
    }

    @Provides
    @Named(DB_TIME_FORMAT_QUALIFIER)
    fun provideDbTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(DB_TIME_FORMAT)
    }

    @Provides
    @Named(DB_DATE_TIME_FORMAT_QUALIFIER)
    fun provideDbDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(DB_DATE_TIME_FORMAT)
    }

    @Provides
    @Named(DISPLAY_DATE_FORMAT_QUALIFIER)
    fun provideDisplayDateFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(DISPLAY_DATE_FORMAT)
    }

    @Provides
    @Named(DISPLAY_TIME_FORMAT_QUALIFIER)
    fun provideDisplayTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(DISPLAY_TIME_FORMAT)
    }

    @Provides
    @Named(DISPLAY_DATE_TIME_FORMAT_QUALIFIER)
    fun provideDisplayDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(DISPLAY_DATE_TIME_FORMAT)
    }
}