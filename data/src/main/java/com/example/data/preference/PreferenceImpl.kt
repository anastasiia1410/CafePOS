package com.example.data.preference

import android.content.Context
import androidx.core.content.edit

class PreferenceImpl(context: Context) : Preference {

    private val sharedPreference =
        context.getSharedPreferences(APP_PREFERENCE_NAME, Context.MODE_PRIVATE)

    override val token: String?
        get() = sharedPreference.getString(TOKEN_KEY, null)

    override fun saveToken(token: String?) {
        sharedPreference.edit {
            putString(TOKEN_KEY, token)
        }
    }

    companion object {
        private const val APP_PREFERENCE_NAME = "app.preferences"
        private const val TOKEN_KEY = "token_key"
    }
}