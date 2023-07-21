package com.example.presentation.screens.home.menu.add_to_menu

import android.app.Application
import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.entity.Menu
import com.example.domain.entity.Portion
import com.example.domain.repository.MenuRepository
import com.example.presentation.utils.createTemporaryFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.File

class AddMenuViewModel(
    private val context: Application,
    private val menuRepository: MenuRepository,
) : ViewModel() {

    val pathFlow = MutableStateFlow<String?>(null)
    val categoryFlow = MutableSharedFlow<Category>()
    private var uri: Uri? = null
    private var categoryMut: Category? = null
    private var portion: Portion? = null

    fun createCameraFile(): Uri {
        val file: File = createTemporaryFile(context)
        pathFlow.value = file.absolutePath
        val result = FileProvider.getUriForFile(
            context,
            "com.example.cafepos.presentation.fileprovider",
            file
        )
        uri = result
        return result
    }


    fun loadCategoryById(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        val category = menuRepository.getCategoryById(id)
        categoryMut = category
        categoryFlow.emit(category)
    }


    fun savePortion(portion: Portion) {
        this.portion = portion
    }

    fun saveMenuItem(title: String, price: String, portionSize: String) =
        viewModelScope.launch {
            val menu = Menu(
                title = title,
                image = pathFlow.value!!,
                price = price.toDouble(),
                portionId = portion!!.id,
                portionSize = portionSize.toInt(),
                categoryId = categoryMut!!.id
            )
            menuRepository.insertMenu(menu)
        }
}
