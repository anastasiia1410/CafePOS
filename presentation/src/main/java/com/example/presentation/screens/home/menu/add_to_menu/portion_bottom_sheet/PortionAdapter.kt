package com.example.presentation.screens.home.menu.add_to_menu.portion_bottom_sheet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.Portion
import com.example.presentation.databinding.ItemCategoryViewBinding

class PortionAdapter : RecyclerView.Adapter<PortionAdapter.VH>() {
    private var typePortions = emptyList<Portion>()
    var onTypePortionClick: ((Portion) -> Unit)? = null

    class VH(val binding: ItemCategoryViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun feelCategories(portion: Portion) {
            binding.tvCategory.text = portion.portionType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemCategoryViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = typePortions.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val portionType = typePortions[position]
        holder.feelCategories(portionType)
        holder.binding.root.setOnClickListener {
            onTypePortionClick?.invoke(portionType)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newPortion: List<Portion>) {
        typePortions = newPortion
        notifyDataSetChanged()
    }

}