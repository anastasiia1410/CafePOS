package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.Category
import com.example.presentation.databinding.BsAddNewCategoryBinding
import com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.BottomCategoryFragment
import com.example.presentation.utils.toEditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNewCategoryFragment : BottomSheetDialogFragment() {
    private var _binding: BsAddNewCategoryBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<AddNewCategoryViewModel>()
    private lateinit var adapter: NewCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewCategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = BsAddNewCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvCategory.adapter = adapter

            adapter.onCategoryClick = { chooseCategory ->
                lifecycleScope.launch {
                    viewModel.varCategory.emit(chooseCategory)
                    etTitle.text = chooseCategory.categoryName.toEditText
                }

                val category = Category(
                    categoryName = chooseCategory.categoryName,
                    icon = chooseCategory.categoryName,
                    itemsMenu = emptyList()
                )

                btAdd.setOnClickListener {
                    viewModel.insertCategory(category)
                    BottomCategoryFragment().show(parentFragmentManager, null)
                    dismiss()
                }
            }
        }
    }
}