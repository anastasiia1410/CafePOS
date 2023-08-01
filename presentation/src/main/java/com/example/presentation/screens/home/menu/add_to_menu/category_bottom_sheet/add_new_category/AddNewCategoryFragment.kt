package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.Category
import com.example.presentation.databinding.BsAddNewCategoryBinding
import com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.BottomCategoryFragment
import com.example.presentation.utils.toEditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNewCategoryFragment : BottomSheetDialogFragment() {
    private var _binding: BsAddNewCategoryBinding? = null
    private val binding get() = _binding ?: error("binding is null")
    private val viewModel by viewModel<AddNewCategoryViewModel>()
    private lateinit var categoryAdapter: NewCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = NewCategoryAdapter()
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
        with(binding.rvCategory) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        with(viewModel) {
            categoryAdapter.onCategoryClick = { chooseCategory ->
                binding.etTitle.text = chooseCategory.categoryName.toEditText
                val category = Category(
                    categoryName = chooseCategory.categoryName,
                    icon = chooseCategory.categoryName,
                    itemsMenu = emptyList()
                )

                binding.btAdd.setOnClickListener {
                    insertCategory(category)
                    BottomCategoryFragment().show(parentFragmentManager, null)
                    dismiss()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
