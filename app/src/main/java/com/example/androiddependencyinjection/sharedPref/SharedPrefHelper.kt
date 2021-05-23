package com.example.androiddependencyinjection.sharedPref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import kotlinx.coroutines.Job

// Always Use PreferenceManager
class SharedPrefHelper {

    companion object {
        private var instance: SharedPrefHelper? = null
        private val LOCK = Job()
        private var prefHelper: SharedPreferences? = null
        private const val LAST_IME = "LAST_TIME"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: build(context).also {
                instance = it
            }
        }

        private fun build(context: Context): SharedPrefHelper {
            prefHelper = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPrefHelper()
        }
    }

    fun setLastTimeUpdated(time: Long) {
        prefHelper?.let {
            it.edit(commit = true) {
                putLong(LAST_IME, time)
            }
        }
    }

    fun getLastTime(): Long? {
        return prefHelper?.getLong(LAST_IME, 0L)
    }
}