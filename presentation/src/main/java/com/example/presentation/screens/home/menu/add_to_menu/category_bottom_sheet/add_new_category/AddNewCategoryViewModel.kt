package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.repository.MenuRepository
import kotlinx.coroutines.launch

class AddNewCategoryViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    fun insertCategory(category: Category) = viewModelScope.launch {
        menuRepository.insertCategory(category)
    }
}