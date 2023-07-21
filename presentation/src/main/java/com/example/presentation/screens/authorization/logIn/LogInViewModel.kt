package com.example.presentation.screens.authorization.logIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.example.domain.repository.UserRepository
import com.example.presentation.screens.authorization.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LogInViewModel(private val userRepository: UserRepository) : ViewModel() {

    val loadingFlow = MutableStateFlow(LoadState.Ready)
    val httpExFlow = MutableSharedFlow<Throwable>()
    val unknownHostExcFlow = MutableSharedFlow<Throwable>()
    val socketTimeoutExFlow = MutableSharedFlow<Throwable>()

    fun logIn(password: String, login: String) = viewModelScope.launch(CoroutineExceptionHandler{ _, throwable ->
        when (throwable) {
            is HttpException -> {
                httpExFlow.tryEmit(throwable)
            }

            is UnknownHostException -> {
                unknownHostExcFlow.tryEmit(throwable)
            }

            is SocketTimeoutException -> {
                socketTimeoutExFlow.tryEmit(throwable)
            }
        }


    }) {
        loadingFlow.emit(LoadState.Loading)
        withContext(Dispatchers.IO) {
            userRepository.logIn(password, login)
        }
        loadingFlow.emit(LoadState.Loaded)
    }
}