package com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.BottomSheetPortionBinding
import com.example.presentation.screens.home.menu.add_to_menu.AddToMenuFragment.Companion.BUNDLE_KEY_TYPE
import com.example.presentation.screens.home.menu.add_to_menu.AddToMenuFragment.Companion.REQUEST_TYPE
import com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet.add_new_portion_type.AddNewPortionTypeFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomPortionFragment : BottomSheetDialogFragment() {
    private var _binding: BottomSheetPortionBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<PortionViewModel>()
    private lateinit var portionAdapter: PortionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        portionAdapter = PortionAdapter()
        viewModel.loadPortionTypes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetPortionBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvCategories) {
            layoutManager = LinearLayoutManager(context)
            adapter = portionAdapter
            portionAdapter.onTypePortionClick = {
                val bundle = Bundle()
                bundle.putParcelable(BUNDLE_KEY_TYPE, it)
                parentFragmentManager.setFragmentResult(REQUEST_TYPE, bundle)
                dismiss()
            }
        }

        binding.tvAddNewCategory.setOnClickListener {
            val newPortionTypeFragment = AddNewPortionTypeFragment()
            newPortionTypeFragment.show(parentFragmentManager, null)
            dismiss()
        }
        lifecycleScope.launch { viewModel.portionTypesFlow.collect { portionAdapter.updateItems(it) } }
    }
}