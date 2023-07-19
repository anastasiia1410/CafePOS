package com.example.domain.repository

interface UserRepository {

     suspend fun registration(password: String, login: String, email: String)

     suspend fun logIn(password: String, login: String)
}