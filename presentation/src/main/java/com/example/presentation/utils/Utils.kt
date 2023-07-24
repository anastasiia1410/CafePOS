package com.example.presentation.utils

import android.text.Editable
import android.widget.TextView
import com.example.domain.entity.Category
import com.example.presentation.R

fun TextView?.inputText(): String {
    return this?.text?.toString() ?: ""
}

val String.toEditText: Editable get() = Editable.Factory.getInstance().newEditable(this)
fun feelCategories(category: Category): Int {
    return when (category.icon) {
        "Coffee" ->   R.drawable.ic_coffee
        "Cake" -> R.drawable.ic_cake
        "Other" -> R.drawable.ic_other
        "Круасан" -> R.drawable.ic_croissant
        "Чай" -> R.drawable.ic_tea
        "Сендвич" -> R.drawable.ic_sandwich
        "Печиво" -> R.drawable.ic_cookie
        "Коктель" -> R.drawable.ic_cocktail
        "Бургер" -> R.drawable.ic_burger
        "Сніданок" ->  R.drawable.ic_breakfast
        else -> {0}
    }
}
