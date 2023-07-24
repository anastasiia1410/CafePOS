package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.BottomSheetCategoryBinding
import com.example.presentation.screens.home.menu.add_to_menu.AddToMenuFragment
import com.example.presentation.screens.home.menu.add_to_menu.AddToMenuFragment.Companion.REQUEST_CATEGORY
import com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category.AddNewCategoryFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomCategoryFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetCategoryBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<CategoryViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryAdapter()
        viewModel.loadCategories()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BottomSheetCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvCategories) {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
            categoryAdapter.onCategoryClick = { category ->
                val bundle = Bundle()
                bundle.putLong(AddToMenuFragment.BUNDLE_KEY_CATEGORY, category.id)
                parentFragmentManager.setFragmentResult(REQUEST_CATEGORY, bundle)
                dismiss()
            }
        }

        binding.tvAddNewCategory.setOnClickListener {
            val newCategoryFragment = AddNewCategoryFragment()
            newCategoryFragment.show(parentFragmentManager, null)
            dismiss()
        }

        lifecycleScope.launch { viewModel.categoryFlow.collect { categoryAdapter.updateItems(it) } }
    }
}

