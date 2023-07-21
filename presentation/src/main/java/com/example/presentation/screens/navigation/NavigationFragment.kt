package com.example.presentation.screens.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationFragment : Fragment() {
    private val viewModel by viewModel<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.screenFlow.collect { startScreen ->
                when (startScreen) {
                    StartScreen.LogIn -> {
                        val action =
                            NavigationFragmentDirections.actionNavigationFragmentToLogInFragment()
                        findNavController().navigate(action)
                    }

                    StartScreen.Home -> {
                        val action =
                            NavigationFragmentDirections.actionNavigationFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }
}


enum class StartScreen {
    LogIn, Home
}