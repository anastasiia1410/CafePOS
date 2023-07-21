package com.example.domain.entity

sealed class CategoryIcon(val name: String) {
    object Coffee : CategoryIcon("Coffee")
    object Desert : CategoryIcon("Cake")
    object Other : CategoryIcon("Other")

    companion object {
        fun getCategoryIconByName(name: String?): CategoryIcon {
            return when (name) {
                Coffee.name -> Coffee
                Desert.name -> Desert
                Other.name -> Other
                else -> throw IllegalArgumentException()
            }
        }
    }
}