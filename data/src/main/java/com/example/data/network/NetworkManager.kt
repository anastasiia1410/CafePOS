package com.example.data.network


interface NetworkManager {

    suspend fun registration(password: String, login: String, email: String)

    suspend fun logIn(password: String, login: String)
}