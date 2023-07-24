package com.example.presentation.screens.home.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.Menu
import com.example.presentation.R
import com.example.presentation.databinding.ItemMenuBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.VH>() {
    private var menuList: MutableList<Menu> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val menu = menuList[position]
        with(holder.binding) {
            tvTitle.text = menu.title
            Glide.with(ivImage)
                .load(menu.image)
                .into(ivImage)
            tvPrice.text =
                tvPrice.context.getString(R.string.pattern_portion_price, menu.price.toString())
            tvPortion.text = tvPortion.context.getString(
                R.string.pattern_portion_size,
                menu.portionSize,
                menu.portion.portionUnit
            )
        }
    }

    override fun getItemCount() = menuList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newMenu: List<Menu>) {
        menuList.clear()
        menuList.addAll(newMenu)
        notifyDataSetChanged()
    }

    class VH(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root)
}
