package com.example.domain.repository

import com.example.data.database.DatabaseManager
import com.example.domain.entity.Category
import com.example.domain.entity.CategoryIcon
import com.example.domain.entity.Menu
import com.example.domain.entity.Portion
import com.example.domain.mapper.toCategory
import com.example.domain.mapper.toCategoryDatabase
import com.example.domain.mapper.toMenu
import com.example.domain.mapper.toMenuDatabase
import com.example.domain.mapper.toPortion
import com.example.domain.mapper.toPortionDatabase


internal class MenuRepositoryImpl(private val databaseManager: DatabaseManager) : MenuRepository {

    override suspend fun insert() {
        if (databaseManager.categoryCount() == 0) {
            val coffeeCategory = Category(
                id = 1L,
                categoryName = "Coffe",
                icon = CategoryIcon.Coffee,
                itemsMenu = emptyList()

            )
            val cakeCategory = Category(
                id = 2L,
                categoryName = "Cake",
                icon = CategoryIcon.Desert,
                itemsMenu = emptyList()
            )
            val otherCategory = Category(
                id = 3L,
                categoryName = "Other",
                icon = CategoryIcon.Other,
                itemsMenu = emptyList()
            )
            databaseManager.insertCategory(coffeeCategory.toCategoryDatabase())
            databaseManager.insertCategory(cakeCategory.toCategoryDatabase())
            databaseManager.insertCategory(otherCategory.toCategoryDatabase())

            val portionG = Portion(1L, "грами", "г")
            val portionMl = Portion(2L, "мілілітри", "мл")
            val portionPc = Portion(3L, "штуки", "шт")

            databaseManager.insertPortion(portionG.toPortionDatabase())
            databaseManager.insertPortion(portionMl.toPortionDatabase())
            databaseManager.insertPortion(portionPc.toPortionDatabase())

            val latte = Menu(
                menuId = 1L,
                title = "Latte",
                image = "https://i.pinimg.com/564x/39/35/d7/3935d7a96e33f58d5ff217963304ce52.jpg",
                price = 75.00,
                portionId = portionMl.id,
                portionSize = 320,
                categoryId = coffeeCategory.id
            )
            val americano = Menu(
                menuId = 2L,
                title = "Americano",
                image = "https://i.pinimg.com/564x/8a/50/9e/8a509e80a255b25b54774a4437debf0e.jpg",
                price = 40.00,
                portionId = portionMl.id,
                portionSize = 150,
                categoryId = coffeeCategory.id
            )
            val cheesecake = Menu(
                menuId = 3L,
                title = "Cheesecake",
                image = "https://i.pinimg.com/474x/53/ff/e2/53ffe2ce6d416ba5dd9492580c4e8251.jpg",
                price = 60.00,
                portionId = portionG.id,
                portionSize = 300,
                categoryId = cakeCategory.id
            )
            val napoleon = Menu(
                menuId = 4L,
                title = "Napoleon",
                image = "https://i.pinimg.com/474x/16/bb/a1/16bba120b5c194ce99785d48f0d1cba8.jpg",
                price = 55.00,
                portionId = portionG.id,
                portionSize = 250,
                categoryId = cakeCategory.id
            )

            databaseManager.insertMenu(latte.toMenuDatabase())
            databaseManager.insertMenu(americano.toMenuDatabase())
            databaseManager.insertMenu(cheesecake.toMenuDatabase())
            databaseManager.insertMenu(napoleon.toMenuDatabase())
        }
    }

    override suspend fun insertCategory(category: Category) {
        databaseManager.insertCategory(category.toCategoryDatabase())
    }

    override suspend fun insertPortion(portion: Portion) {
        databaseManager.insertPortion(portion.toPortionDatabase())
    }

    override suspend fun getAllMenu(): List<Category> {
        val menuWithCategoryAndPortion = databaseManager.getMenuWithCategoryAndPortion()
        return menuWithCategoryAndPortion.map { menuWithCategory ->
            val menuItems = menuWithCategory.menuList.map { menuWithPortion ->
                menuWithPortion.menu.toMenu()
            }
            menuWithCategory.category.toCategory(menuItems)
        }
    }

    override suspend fun getCategoryById(id: Long): Category {
        val categoryById = databaseManager.getCategoryById(id)
        return categoryById.toCategory(emptyList())
    }

    override suspend fun getPortionById(id: Long): Portion {
        val portionById = databaseManager.getPortionById(id)
        return portionById.toPortion()
    }

    override suspend fun getPortionView(): List<Portion> {
        val portionViews = databaseManager.getAllPortions()
        return portionViews.map { it.toPortion() }
    }

    override suspend fun insertMenu(menu: Menu) {
        databaseManager.insertMenu(menu.toMenuDatabase())
    }
}