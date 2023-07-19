package com.example.presentation.screens.authorization.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.core.BaseFragment
import com.example.presentation.databinding.FragmentRegistrationBinding
import com.example.presentation.screens.authorization.LoadState
import com.example.presentation.utils.inputText
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    private val viewModel by viewModel<RegistrationViewModel>()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater, container, false)
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
                        val action = RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
        with(binding) {
            btGo.setOnClickListener {
                viewModel.registration(
                    etLogin.inputText(),
                    etPassword.inputText(),
                    etEmail.inputText()
                )
            }
        }
    }
}
