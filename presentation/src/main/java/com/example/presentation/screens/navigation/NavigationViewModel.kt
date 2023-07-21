package com.example.presentation.screens.navigation

import androidx.lifecycle.ViewModel
import com.example.domain.repository.PreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationViewModel(preferenceRepository: PreferenceRepository) : ViewModel() {
    val screenFlow = MutableStateFlow(StartScreen.LogIn)

    init {
        if (preferenceRepository.token == null) {
            screenFlow.tryEmit(StartScreen.LogIn)
        } else {
            screenFlow.tryEmit(StartScreen.Home)
        }
    }
}