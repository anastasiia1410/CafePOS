package com.example.presentation.utils

import android.widget.TextView
import com.example.domain.entity.Category
import com.example.domain.entity.CategoryIcon
import com.example.presentation.R

fun TextView?.inputText(): String {
    return this?.text?.toString() ?: ""
}
fun feelCategories(category: Category): Int {
    val icon = when (category.icon) {
        CategoryIcon.Coffee -> R.drawable.ic_coffee
        CategoryIcon.Desert -> R.drawable.ic_cake
        CategoryIcon.Other -> R.drawable.ic_other

    }
    return icon
}
