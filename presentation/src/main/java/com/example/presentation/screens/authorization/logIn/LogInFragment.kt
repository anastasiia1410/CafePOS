package com.example.presentation.screens.authorization.logIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.core.BaseFragment
import com.example.presentation.databinding.FragmentLogInBinding
import com.example.presentation.screens.authorization.LoadState
import com.example.presentation.utils.inputText
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : BaseFragment<FragmentLogInBinding>() {
    private val viewModel by viewModel<LogInViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentLogInBinding {
        return FragmentLogInBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingFlow.collect { loadState ->
                when (loadState) {
                    LoadState.Ready -> binding.progressBar.isVisible = false
                    LoadState.Loading -> binding.progressBar.isVisible = true
                    LoadState.Loaded -> {
                        binding.progressBar.isVisible = false
                        val action = LogInFragmentDirections.actionLogInFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.httpExFlow.collect {
                showSnackBar(binding.btGo, R.string.http_exeption)
                binding.progressBar.isVisible = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.unknownHostExcFlow.collect {
                showSnackBar(binding.btGo, R.string.unknown_host_exeption)
                binding.progressBar.isVisible = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.socketTimeoutExFlow.collect {
                showSnackBar(binding.btGo, R.string.time_out_socket_ex)
                binding.progressBar.isVisible = false
            }
        }

        with(binding) {
            btGo.setOnClickListener {
                viewModel.logIn(
                    etLogin.inputText(),
                    etPassword.inputText()
                )
            }

            tvNoAcc.setOnClickListener {
                val action = LogInFragmentDirections.actionLogInFragmentToRegistrationFragment()
                findNavController().navigate(action)
            }
        }
    }
}
