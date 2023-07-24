package com.example.presentation.di

import com.example.presentation.screens.authorization.logIn.LogInViewModel
import com.example.presentation.screens.authorization.registration.RegistrationViewModel
import com.example.presentation.screens.home.menu.MenuViewModel
import com.example.presentation.screens.home.menu.add_to_menu.AddMenuViewModel
import com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.CategoryViewModel
import com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category.AddNewCategoryViewModel
import com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet.PortionViewModel
import com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet.add_new_portion_type.AddNewPortionViewModel
import com.example.presentation.screens.navigation.NavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LogInViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { AddMenuViewModel(get(), get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { PortionViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { NavigationViewModel(get()) }
    viewModel { AddNewCategoryViewModel(get()) }
    viewModel { AddNewPortionViewModel(get()) }
}