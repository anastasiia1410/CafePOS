package com.example.presentation.screens.home.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    val categoryFlow = MutableStateFlow<List<Category>>(emptyList())

    init {
        viewModelScope.launch {
            menuRepository.insert()
        }

    }
    fun getMenu() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = menuRepository.getAllMenu()
                categoryFlow.emit(list)
            }
        }
    }
}