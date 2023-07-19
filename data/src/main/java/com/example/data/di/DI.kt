package com.example.data.di

import com.example.data.database.DatabaseManager
import com.example.data.database.DatabaseManagerImpl
import com.example.data.network.NetworkManager
import com.example.data.network.NetworkManagerImpl
import com.example.data.preference.Preference
import com.example.data.preference.PreferenceImpl
import org.koin.dsl.module


val dataModule = module {
    single<NetworkManager> { NetworkManagerImpl(get()) }
    single<Preference> { PreferenceImpl(get()) }
    single<DatabaseManager>{DatabaseManagerImpl(get())}
}