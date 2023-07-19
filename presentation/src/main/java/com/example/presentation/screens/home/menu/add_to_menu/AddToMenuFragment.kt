package com.example.presentation.screens.home.menu.add_to_menu

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.core.BaseFragment
import com.example.presentation.databinding.FragmentAddToMenuBinding
import com.example.presentation.screens.home.menu.add_to_menu.bottom.BottomCategoryFragment
import com.example.presentation.screens.home.menu.add_to_menu.bottom.BottomPortionFragment
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
            tvChooseCategory.setOnClickListener {
                val bottomCategoryFragment = BottomCategoryFragment()
                bottomCategoryFragment.show(childFragmentManager, null)
                bottomCategoryFragment.callback = { category, drawableId ->
                    setCategory(category, drawableId)
                }
            }
            tvPortionType.setOnClickListener {
                val bottomPortionFragment = BottomPortionFragment()
                bottomPortionFragment.show(childFragmentManager, null)
                bottomPortionFragment.callback = { type ->
                    tvPortionType.text = type
                }
            }

            ivAddPhoto.setOnClickListener { launcher.launch(viewModel.createCameraFile()) }
        }
    }

    private fun setPhoto(path: String) {
        binding.tvMakePhoto.isVisible = false
        Glide.with(binding.ivAddPhoto)
            .load(path)
            .into(binding.ivAddPhoto)
    }

    private fun setCategory(category: String, drawableId: Int) {
        binding.tvChooseCategory.text = category
        binding.tvChooseCategory.setTextColor(resources.getColor(R.color.black, null))
        binding.tvChooseCategory.setCompoundDrawablesRelativeWithIntrinsicBounds(
            drawableId,
            0,
            0,
            0
        )
    }

}