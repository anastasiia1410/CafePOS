package com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet.add_new_portion_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Portion
import com.example.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewPortionViewModel(private val menuRepository: MenuRepository) : ViewModel() {

    fun insertPortion(portion: Portion) = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.insertPortion(portion)
    }
}