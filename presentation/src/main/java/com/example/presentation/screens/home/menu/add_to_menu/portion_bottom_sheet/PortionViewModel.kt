package com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Portion
import com.example.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PortionViewModel(private val menuRepository: MenuRepository) : ViewModel() {
    val portionTypesFlow = MutableStateFlow<List<Portion>>(emptyList())

    fun loadPortionTypes() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = menuRepository.getPortionView()
            portionTypesFlow.emit(list)
        }
    }
}
