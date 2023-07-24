package com.example.domain.mapper

import com.example.data.database.entity.CategoryDatabase
import com.example.data.database.entity.MenuDatabase
import com.example.data.database.entity.PortionDatabase
import com.example.domain.entity.Category
import com.example.domain.entity.Menu
import com.example.domain.entity.Portion

fun Menu.toMenuDatabase(): MenuDatabase {
    return MenuDatabase(
        menuId = this.menuId,
        title = this.title,
        image = this.image,
        categoryId = this.categoryId,
        portionId = this.portion.id,
        price = this.price,
        portionSize = this.portionSize
    )
}

fun MenuDatabase.toMenu(portion: PortionDatabase): Menu {
    return Menu(
        menuId = this.menuId,
        title = this.title,
        image = this.image,
        categoryId = this.categoryId,
        portion = portion.toPortion(),
        price = this.price,
        portionSize = this.portionSize
    )
}

fun Category.toCategoryDatabase(): CategoryDatabase {
    return CategoryDatabase(
        id = this.id,
        categoryName = this.categoryName,
        icon = this.icon
    )
}


fun CategoryDatabase.toCategory(itemsMenu: List<Menu>): Category {
    return Category(
        id = this.id,
        categoryName = this.categoryName,
        icon = this.icon,
        itemsMenu = itemsMenu
    )
}

fun Portion.toPortionDatabase(): PortionDatabase {
    return PortionDatabase(
        id = this.id,
        portionType = this.portionType,
        portionUnit = this.portionUnit
    )
}

fun PortionDatabase.toPortion(): Portion {
    return Portion(
        id = this.id,
        portionType = this.portionType,
        portionUnit = this.portionUnit
    )
}





