package com.example.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val navigationFlow = MutableStateFlow(false)

    fun navigateToAddToMenu() = viewModelScope.launch {
        navigationFlow.emit(true)
    }
}