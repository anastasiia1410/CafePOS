package com.example.domain.mapper

import com.example.data.database.entity.CategoryDatabase
import com.example.data.database.entity.CategoryIconDatabase
import com.example.data.database.entity.MenuDatabase
import com.example.data.database.entity.PortionDatabase
import com.example.data.database.entity.PortionTypeDatabase
import com.example.domain.entity.Category
import com.example.domain.entity.CategoryIcon
import com.example.domain.entity.Menu
import com.example.domain.entity.Portion
import com.example.domain.entity.PortionType

fun Menu.toMenuDatabase(): MenuDatabase {
    return MenuDatabase(
        menuId = this.menuId,
        title = this.title,
        image = this.image,
        categoryId = this.categoryId,
        portionId = this.portionId,
        price = this.price,
        portionSize = this.portionSize
    )
}

fun MenuDatabase.toMenu(): Menu {
    return Menu(
        menuId = this.menuId,
        title = this.title,
        image = this.image,
        categoryId = this.categoryId,
        portionId = this.portionId,
        price = this.price,
        portionSize = this.portionSize
    )
}

fun Category.toCategoryDatabase(): CategoryDatabase {
    return CategoryDatabase(
        id = this.id,
        categoryName = this.categoryName,
        icon = this.icon.toIconCategoryDatabase()
    )
}


fun CategoryDatabase.toCategory(itemsMenu: List<Menu>): Category {
    return Category(
        id = this.id,
        categoryName = this.categoryName,
        icon = this.icon.toIconCategory(),
        itemsMenu = itemsMenu
    )
}

fun Portion.toPortionDatabase(): PortionDatabase {
    return PortionDatabase(
        id = this.id,
        portionType = this.portionType.toPortionTypeDatabase(),
    )
}

fun PortionDatabase.toPortion(): Portion {
    return Portion(
        id = this.id,
        portionType = this.portionType.toPortionType(),
    )
}

fun CategoryIconDatabase.toIconCategory(): CategoryIcon {
    return when (this) {
        CategoryIconDatabase.Coffee -> CategoryIcon.Coffee
        CategoryIconDatabase.Desert -> CategoryIcon.Desert
        CategoryIconDatabase.Other -> CategoryIcon.Other
    }
}

fun CategoryIcon.toIconCategoryDatabase(): CategoryIconDatabase {
    return when (this) {
        CategoryIcon.Coffee -> CategoryIconDatabase.Coffee
        CategoryIcon.Desert -> CategoryIconDatabase.Desert
        CategoryIcon.Other -> CategoryIconDatabase.Other
    }
}

fun PortionType.toPortionTypeDatabase(): PortionTypeDatabase {
    return when (this) {
        PortionType.Grams -> PortionTypeDatabase.Grams
        PortionType.Millilitres -> PortionTypeDatabase.Millilitres
        PortionType.Pieces -> PortionTypeDatabase.Pieces
    }
}

fun PortionTypeDatabase.toPortionType(): PortionType {
    return when (this) {
        PortionTypeDatabase.Grams -> PortionType.Grams
        PortionTypeDatabase.Millilitres -> PortionType.Millilitres
        PortionTypeDatabase.Pieces -> PortionType.Pieces
    }
}