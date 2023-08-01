package com.example.presentation.screens.home.menu.add_to_menu

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.BundleCompat
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.entity.Portion
import com.example.presentation.R
import com.example.presentation.core.BaseFragment
import com.example.presentation.databinding.FragmentAddToMenuBinding
import com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.BottomCategoryFragment
import com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet.BottomPortionFragment
import com.example.presentation.utils.feelCategories
import com.example.presentation.utils.inputText
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddToMenuFragment : BaseFragment<FragmentAddToMenuBinding>() {
    private lateinit var launcher: ActivityResultLauncher<Uri>
    private val viewModel by viewModel<AddMenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contract = ActivityResultContracts.TakePicture()
        launcher = registerForActivityResult(contract) { result ->
            if (result) {
                viewModel.pathFlow.value?.let { setPhoto(it) }
            }
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): FragmentAddToMenuBinding {
        return FragmentAddToMenuBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setFragmentResultListener(REQUEST_CATEGORY) { _, bundle ->
                val id = bundle.getLong(BUNDLE_KEY_CATEGORY)
                viewModel.loadCategoryById(id)
            }
            setFragmentResultListener(REQUEST_TYPE) { _, bundle ->
                val portion =
                    BundleCompat.getParcelable(bundle, BUNDLE_KEY_TYPE, Portion::class.java)
                        ?: error("empty parcelable")
                binding.tvPortionType.text = portion.portionType
                viewModel.savePortion(portion)
            }

            lifecycleScope.launch {
                viewModel.categoryFlow.collect { category ->
                    tvChooseCategory.text = category.categoryName
                    tvChooseCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        feelCategories(
                            category
                        ), 0, 0, 0
                    )
                }
            }

            binding.tvChooseCategory.setOnClickListener {
                val categoryBottomSheet = BottomCategoryFragment()
                categoryBottomSheet.show(parentFragmentManager, null)
            }
            binding.tvPortionType.setOnClickListener {
                val portionTypeBottomSheet = BottomPortionFragment()
                portionTypeBottomSheet.show(parentFragmentManager, null)
            }

            btAdd.setOnClickListener {
                viewModel.saveMenuItem(
                    title = binding.etName.inputText(),
                    price = binding.etPrice.inputText(),
                    portionSize = binding.etPortion.inputText()
                )
                findNavController().popBackStack()
            }

            ivAddPhoto.setOnClickListener { launcher.launch(viewModel.createCameraFile()) }

            binding.ivArrowBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun setPhoto(path: String) {
        binding.tvMakePhoto.isVisible = false
        Glide.with(binding.ivAddPhoto)
            .load(path)
            .into(binding.ivAddPhoto)
    }

    companion object {
        const val REQUEST_CATEGORY = "requestKeyCategory"
        const val BUNDLE_KEY_CATEGORY = "bundleKeyCategory"
        const val REQUEST_TYPE = "requestKeyType"
        const val BUNDLE_KEY_TYPE = "bundleKeyType"
    }

}