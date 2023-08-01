package com.example.data.network

import com.example.common.exeptions.APIException
import com.example.data.network.entity.request.SignUpRequest
import com.example.data.preference.Preference
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class NetworkManagerImpl(private val preference: Preference) : NetworkManager {
    private val gson: Gson
    private val client: OkHttpClient
    private val retrofit: Retrofit
    private val api: Api

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        gson = GsonBuilder().serializeNulls().create()
        retrofit = Retrofit.Builder()
            .baseUrl("https://parseapi.back4app.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(Api::class.java)
    }

    override suspend fun registration(password: String, login: String, email: String) {
        val response = api.registration(SignUpRequest(password, login, email))
        preference.saveToken(response.token)
    }

    override suspend fun logIn(password: String, login: String) {
        try {
            val response = api.logIn(login, password)
            preference.saveToken(response.token)
        } catch (t: HttpException) {
            throw APIException(statusCode = t.code())
        }
    }
}