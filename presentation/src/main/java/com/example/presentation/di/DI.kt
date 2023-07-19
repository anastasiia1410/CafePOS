package com.example.presentation.di

import com.example.presentation.screens.authorization.logIn.LogInViewModel
import com.example.presentation.screens.authorization.registration.RegistrationViewModel
import com.example.presentation.screens.home.menu.add_to_menu.AddMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LogInViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { AddMenuViewModel(get(), get()) }
}