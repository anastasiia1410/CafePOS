package com.example.presentation.screens.home.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    fun insert() = viewModelScope.launch {
        menuRepository.insert()
    }
}