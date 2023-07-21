package com.example.presentation.screens.home.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Category
import com.example.presentation.databinding.ItemCategoryBinding
import com.example.presentation.utils.feelCategories

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    private var categoryList: MutableList<Category> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        return CategoryVH(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category = categoryList[position]
        holder.menuAdapter.updateItems(category.itemsMenu)
        with(holder.binding) {
            tvCategoryName.text = category.categoryName
            ivCategoryImage.setImageResource(
                feelCategories(
                    category
                )
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newCategory: List<Category>) {
        categoryList.clear()
        categoryList.addAll(newCategory)
        notifyDataSetChanged()
    }

    class CategoryVH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val menuAdapter = MenuAdapter()

        init {
            binding.rvInnerRecycler.layoutManager =
                LinearLayoutManager(
                    binding.rvInnerRecycler.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.rvInnerRecycler.adapter = menuAdapter
        }
    }

}