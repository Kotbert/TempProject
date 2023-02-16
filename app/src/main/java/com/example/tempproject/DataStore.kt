package com.example.tempproject

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tempproject.bininfo.BinInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    val getBinHistory: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[BIN_HISTORY_KEY] ?: ""
    }

    suspend fun saveBIN(bin: BinInfo) {
        context.dataStore.edit { preferences ->
            preferences[BIN_HISTORY_KEY] = bin.toString()
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "bin_history")
        val BIN_HISTORY_KEY = stringPreferencesKey("bin_history_key")
    }
}