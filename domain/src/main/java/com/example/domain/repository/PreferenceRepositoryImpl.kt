package com.example.domain.repository

import com.example.data.preference.Preference

class PreferenceRepositoryImpl(private val preferences: Preference) : PreferenceRepository {
    override val token: String?
        get() = preferences.token
}