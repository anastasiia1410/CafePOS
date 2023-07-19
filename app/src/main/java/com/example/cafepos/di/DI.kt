package com.example.cafepos.di

import com.example.cafepos.App
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun App.initKoin() {
    startKoin {
        androidContext(this@initKoin)
        modules(presentationModule, domainModule, dataModule)
    }
}