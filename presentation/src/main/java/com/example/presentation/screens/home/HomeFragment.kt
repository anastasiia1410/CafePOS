package com.example.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.presentation.R
import com.example.presentation.core.BaseFragment
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.screens.home.Page.Companion.pageByPosition
import com.example.presentation.screens.home.menu.MenuFragment
import com.example.presentation.screens.home.orders.OrdersFragment
import com.example.presentation.screens.home.settings.SettingsFragment
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by activityViewModels<HomeViewModel>()

    private val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bnBottomNavigation.selectedItemId = pageByPosition(position).menuId

        }
    }
    private lateinit var adapter: AdapterViewPager

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterViewPager(
            items = listOf(Page.Orders, Page.Menu, Page.Settings),
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigationFlow.collect { isNavigate ->
                if (isNavigate) {
                    viewModel.navigationFlow.emit(false)
                    val action = HomeFragmentDirections.actionHomeFragmentToAddToMenuFragment()
                    findNavController().navigate(action)
                }
            }
        }

        with(binding) {
            vpViewPager.adapter = adapter
            bnBottomNavigation.setOnItemSelectedListener { item ->
                val page = Page.pageById(item.itemId)
                vpViewPager.currentItem = page.position
                return@setOnItemSelectedListener true
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.vpViewPager.registerOnPageChangeCallback(callback)
    }

    override fun onStop() {
        super.onStop()
        binding.vpViewPager.unregisterOnPageChangeCallback(callback)
    }
}

sealed class Page(val position: Int, val menuId: Int) {
    object Orders : Page(0, R.id.page_orders)
    object Menu : Page(1, R.id.page_menu)
    object Settings : Page(2, R.id.page_settings)

    companion object {
        fun pageByPosition(position: Int): Page {
            return when (position) {
                Orders.position -> Orders
                Menu.position -> Menu
                Settings.position -> Settings
                else -> throw IllegalArgumentException()
            }
        }

        fun pageById(menuId: Int): Page {
            return when (menuId) {
                Orders.menuId -> Orders
                Menu.menuId -> Menu
                Settings.menuId -> Settings
                else -> throw IllegalArgumentException()
            }
        }
    }
}

class AdapterViewPager(
    private val items: List<Page>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        return when (items[position]) {
            Page.Orders -> OrdersFragment()
            Page.Menu -> MenuFragment()
            Page.Settings -> SettingsFragment()
        }
    }
}
