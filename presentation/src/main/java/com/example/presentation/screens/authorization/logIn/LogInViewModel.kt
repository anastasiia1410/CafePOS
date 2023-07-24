package com.example.presentation.screens.authorization.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.UserRepository
import com.example.presentation.screens.authorization.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogInViewModel(private val userRepository: UserRepository) : ViewModel() {

    val loadingFlow = MutableStateFlow(LoadState.Ready)
    val exFlow = MutableStateFlow(Throwable())


    fun logIn(password: String, login: String) =
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            exFlow.value = throwable
        }) {
            loadingFlow.emit(LoadState.Loading)
            withContext(Dispatchers.IO) {
                userRepository.logIn(password, login)
            }
            loadingFlow.emit(LoadState.Loaded)
        }
}