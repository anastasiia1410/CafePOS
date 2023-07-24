package com.example.presentation.screens.home.menu.add_to_menu.category_bottom_sheet.add_new_category

import com.example.presentation.R

data class VarCategory(
    val categoryName: String,
    val icon: Int,
)  {
    companion object {
        fun insertCategory(): List<VarCategory> {
            val list = mutableListOf<VarCategory>()
            list.add(VarCategory("Круасан", R.drawable.ic_croissant))
            list.add(VarCategory("Чай", R.drawable.ic_tea))
            list.add(VarCategory("Сендвич", R.drawable.ic_sandwich))
            list.add(VarCategory("Печиво", R.drawable.ic_cookie))
            list.add(VarCategory("Коктель", R.drawable.ic_cocktail))
            list.add(VarCategory("Бургер", R.drawable.ic_burger))
            list.add(VarCategory("Сніданок", R.drawable.ic_breakfast))
            return list
        }
    }
}
