package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemIconCategoryBinding

class NewCategoryAdapter : RecyclerView.Adapter<NewCategoryAdapter.VH>() {
    private var categoryIcons = VarCategory.insertCategory()

    var onCategoryClick: ((VarCategory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemIconCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoryIcons.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val category = categoryIcons[position]
        holder.binding.ivIcon.setImageResource(category.icon)
        holder.binding.ivIcon.setOnClickListener {
                holder.binding.ivIcon.setBackgroundResource(R.color.light_pink)
                onCategoryClick?.invoke(category)
        }
    }

    class VH(val binding: ItemIconCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}