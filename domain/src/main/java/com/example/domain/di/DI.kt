package com.example.domain.di

import com.example.domain.repository.MenuRepository
import com.example.domain.repository.MenuRepositoryImpl
import com.example.domain.repository.PreferenceRepository
import com.example.domain.repository.PreferenceRepositoryImpl
import com.example.domain.repository.UserRepository
import com.example.domain.repository.UserRepositoryImpl
import org.koin.dsl.module

val domainModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
    factory<MenuRepository> { MenuRepositoryImpl(get()) }
    factory<PreferenceRepository> { PreferenceRepositoryImpl(get()) }
}