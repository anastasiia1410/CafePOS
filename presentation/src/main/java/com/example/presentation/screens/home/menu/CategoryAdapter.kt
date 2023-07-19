package com.example.presentation.screens.home.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
//        val category = emptyList<>()
//        with(holder.binding){
//            tvCategoryName.text = category.categoryName
//        }
    }

    class CategoryVH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}