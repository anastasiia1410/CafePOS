package com.example.presentation.screens.home.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.core.BaseFragment
import com.example.presentation.databinding.FragmentMenuBinding
import com.example.presentation.screens.home.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val viewModel by viewModel<MenuViewModel>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryAdapter()
        viewModel.getMenu()
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentMenuBinding {
        return FragmentMenuBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvOuterRecycler.layoutManager = LinearLayoutManager(requireContext())
            rvOuterRecycler.adapter = categoryAdapter

            btAdd.setOnClickListener {
                homeViewModel.navigateToAddToMenu()
            }

            lifecycleScope.launch {
                viewModel.categoryFlow.collect {
                    categoryAdapter.updateItems(it)
                }
            }
        }
    }
}