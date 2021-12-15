package com.sk.outlay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

const val IO_DISPATCHER_QUALIFIER = "IODispatcher"
const val DEFAULT_DISPATCHER_QUALIFIER = "DefaultDispatcher"
const val MAIN_DISPATCHER_QUALIFIER = "MainDispatcher"
const val APP_COROUTINE_SCOPE_QUALIFIER = "AppScope"

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @Provides
    @Singleton
    @Named(APP_COROUTINE_SCOPE_QUALIFIER)
    fun providesApplicationScope(
        @Named(IO_DISPATCHER_QUALIFIER) dispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }

    @Provides
    @Named(DEFAULT_DISPATCHER_QUALIFIER)
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @Named(IO_DISPATCHER_QUALIFIER)
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Named(MAIN_DISPATCHER_QUALIFIER)
    fun provideMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}