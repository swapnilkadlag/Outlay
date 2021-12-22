package com.sk.outlay.ui.categories

import androidx.lifecycle.ViewModel
import com.sk.outlay.data.Repository
import com.sk.outlay.data.entities.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    fun getCategories(): Flow<List<Category>> {
        return repository.getCategories()
    }
}