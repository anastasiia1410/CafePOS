package com.example.presentation.screens.authorization.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.UserRepository
import com.example.presentation.screens.authorization.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationViewModel(private val userRepository: UserRepository) : ViewModel() {

    val loadingFlow = MutableStateFlow(LoadState.Ready)

    fun registration(password: String, login: String, email: String) = viewModelScope.launch {
        loadingFlow.emit(LoadState.Loading)
        withContext(Dispatchers.IO) {
            userRepository.registration(password, login, email)
        }
        loadingFlow.emit(LoadState.Loaded)
    }
}