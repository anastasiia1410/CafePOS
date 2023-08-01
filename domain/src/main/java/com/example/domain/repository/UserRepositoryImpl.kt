package com.example.domain.repository

import com.example.data.network.NetworkManager

internal class UserRepositoryImpl(private val networkManager: NetworkManager) : UserRepository {

    override suspend fun registration(password: String, login: String, email: String) {
        networkManager.registration(password, login, email)
    }

    override suspend fun logIn(password: String, login: String) {
        networkManager.logIn(password, login)

    }
}