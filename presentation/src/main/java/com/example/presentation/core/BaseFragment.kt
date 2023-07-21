package com.example.presentation.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding ?: error("binding isn`t set")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = createBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    protected abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    protected open fun addWindowInsets(
        view: View,
        @WindowInsetsCompat.Type.InsetsType type: Int = WindowInsetsCompat.Type.systemBars(),
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v: View, insets: WindowInsetsCompat ->
            val systemBarInsets = insets.getInsets(type)
            v.setPadding(
                systemBarInsets.left,
                systemBarInsets.top,
                systemBarInsets.right,
                systemBarInsets.bottom
            )
            insets
        }
    }

    protected fun showToast(@StringRes text: Int) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    protected fun showSnackBar(view: View, @StringRes text: Int) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
    }
}