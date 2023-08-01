package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemIconCategoryBinding

class NewCategoryAdapter : RecyclerView.Adapter<NewCategoryAdapter.VH>() {
    private var categoryIcons = VarCategory.insertCategory()
    private var selectedIndex = -1

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val category = categoryIcons[position]
        holder.binding.ivIcon.setImageResource(category.icon)

        holder.binding.ivIcon.setBackgroundColor(
            ContextCompat.getColor(
                holder.binding.ivIcon.context,
                if (selectedIndex == holder.adapterPosition) R.color.light_pink else android.R.color.transparent
            )
        )

        holder.binding.ivIcon.setOnClickListener {
            if (selectedIndex != holder.adapterPosition) {
                if (selectedIndex != -1) {
                    categoryIcons[selectedIndex].isChoose = false
                }
                category.isChoose = true
                selectedIndex = holder.adapterPosition
                notifyDataSetChanged()
                onCategoryClick?.invoke(category)
            }
        }
    }

    class VH(val binding: ItemIconCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}