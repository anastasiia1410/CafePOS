package com.example.presentation.utils

import android.content.Context
import android.widget.TextView
import com.example.domain.entity.Category
import com.example.domain.entity.CategoryIcon
import com.example.domain.entity.PortionType
import com.example.presentation.R

fun TextView?.inputText(): String {
    return this?.text?.toString() ?: ""
}

fun PortionType.toPortionTitle(context: Context): String {
    return when (this) {
        PortionType.Grams -> context.getString(R.string.grams_abr)
        PortionType.Millilitres -> context.getString(R.string.milliliters_abr)
        PortionType.Pieces -> context.getString(R.string.pieces_abr)
    }
}

fun feelCategories(category: Category): Int {
    val icon = when (category.icon) {
        CategoryIcon.Coffee -> R.drawable.ic_coffee
        CategoryIcon.Desert -> R.drawable.ic_cake
        CategoryIcon.Other -> R.drawable.ic_other

    }
    return icon
}
