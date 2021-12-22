package com.sk.outlay.ui.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sk.outlay.data.Repository
import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.entities.Category
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.di.APP_COROUTINE_SCOPE_QUALIFIER
import com.sk.outlay.di.IO_DISPATCHER_QUALIFIER
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Named

class CategoryEditorViewModel @AssistedInject constructor(
    private val repository: Repository,
    @Named(APP_COROUTINE_SCOPE_QUALIFIER) val appScope: CoroutineScope,
    @Named(IO_DISPATCHER_QUALIFIER) val dispatcher: CoroutineDispatcher,
    @Assisted private val categoryId: UUID?,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(accountId: UUID?): CategoryEditorViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            categoryId: UUID?,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(categoryId) as T
            }
        }
    }

    private var category: Category? = null

    var categoryName by mutableStateOf("")

    private val isNewCategory get() = categoryId == null

    val title get() = if (isNewCategory) "Add Category" else "Edit Category"

    fun loadInitialData() {
        if (categoryId != null) {
            loadCategory(categoryId)
        }
    }

    private fun loadCategory(categoryId: UUID) {
        viewModelScope.launch(dispatcher) {
            category = repository.getCategory(categoryId)
            categoryName = category!!.name
        }
    }

    fun saveCategory() {
        appScope.launch(dispatcher) {
            if (isNewCategory) {
                repository.createCategory(categoryName)
            } else {
                val updatedCategory = category!!.copy(
                    name = categoryName,
                )
                repository.updateCategory(updatedCategory)
            }
        }
    }
}