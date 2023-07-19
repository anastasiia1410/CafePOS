package com.example.presentation.screens.home.menu.add_to_menu.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.databinding.BottomSheetPortionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomPortionFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetPortionBinding

    var callback: ((type: String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomSheetPortionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvGrams.setOnClickListener {
                callback?.invoke(getString(R.string.grams))
                dismiss()
            }

            tvMilliliters.setOnClickListener {
                callback?.invoke(getString(R.string.milliliters))
                dismiss()
            }

            tvPieces.setOnClickListener {
                callback?.invoke(getString(R.string.pieces))
                dismiss()
            }
        }
    }


}