package com.example.cafepos

import android.app.Application
import com.example.cafepos.di.initKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}