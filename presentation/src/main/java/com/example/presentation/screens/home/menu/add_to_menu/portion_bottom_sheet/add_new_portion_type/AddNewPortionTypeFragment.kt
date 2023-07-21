package com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet.add_new_portion_type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.entity.Portion
import com.example.presentation.databinding.BottomSheetAddNewPortionTypeBinding
import com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet.BottomPortionFragment
import com.example.presentation.utils.inputText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNewPortionTypeFragment : BottomSheetDialogFragment() {
    private var _binding: BottomSheetAddNewPortionTypeBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<AddNewPortionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetAddNewPortionTypeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            btAdd.setOnClickListener {
                viewModel.insertPortion(
                    Portion(
                        portionType = etTitle.inputText(),
                        portionUnit = etUnit.inputText()
                    )
                )
                BottomPortionFragment().show(parentFragmentManager, null)
                dismiss()
            }
        }
    }
}