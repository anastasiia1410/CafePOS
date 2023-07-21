package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    val categoryFlow = MutableStateFlow<List<Category>>(emptyList())

    fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = menuRepository.getAllMenu()
            categoryFlow.emit(list)
        }
    }
}
