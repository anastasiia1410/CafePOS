package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Category
import com.example.presentation.databinding.ItemCategoryBinding
import com.example.presentation.utils.feelCategories

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.VH>() {

    private var categories: MutableList<Category> = mutableListOf()
    var onCategoryClick: ((Category) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val category = categories[position]
        val icon = feelCategories(category)
        holder.binding.ivCategoryImage.setImageResource(icon)
        holder.binding.tvCategoryName.text = category.categoryName
        holder.binding.root.setOnClickListener {
            onCategoryClick?.invoke(category)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newCategory: List<Category>) {
        categories.clear()
        categories.addAll(newCategory)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}