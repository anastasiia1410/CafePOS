package com.example.presentation.screens.home.menu.add_to_menu.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.BottomSheetCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomCategoryFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetCategoryBinding

    var callback: ((category: String, drawableId: Int) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomSheetCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCake.setOnClickListener {
            callback?.invoke(getString(R.string.cake), R.drawable.ic_cake)
            dismiss()
        }

        binding.tvCoffee.setOnClickListener {
            callback?.invoke(getString(R.string.coffee), R.drawable.ic_coffee)
            dismiss()
        }
    }
}
