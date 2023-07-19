package com.example.presentation.screens.home.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.presentation.core.BaseFragment
import com.example.presentation.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }
}