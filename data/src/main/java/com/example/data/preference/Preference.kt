package com.example.data.preference

interface Preference {

    val token: String?

    fun saveToken(token: String?)

}