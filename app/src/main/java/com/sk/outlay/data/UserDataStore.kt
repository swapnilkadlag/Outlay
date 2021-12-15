package com.sk.outlay.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class UserDataStore(private val context: Context) {
    private val userSessionIdKey = stringPreferencesKey("user_session_id")
    private val hasCompletedSetupKey = booleanPreferencesKey("has_completed_setup")
    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "bestwatch_user_data")

    private suspend fun <V> getValue(key: Preferences.Key<V>): V? {
        val data = context.userDataStore.data.first()
        return data[key]
    }

    private suspend fun <V> setValue(key: Preferences.Key<V>, value: V) {
        context.userDataStore.edit { data ->
            data[key] = value
        }
    }

    suspend fun getSessionId() = getValue(userSessionIdKey)

    suspend fun setSessionId(sessionId: String) = setValue(userSessionIdKey, sessionId)

    suspend fun getHasCompletedSetup() = getValue(hasCompletedSetupKey)

    suspend fun setHasCompletedSetup(hasCompletedSetup: Boolean) = setValue(hasCompletedSetupKey, hasCompletedSetup)
}