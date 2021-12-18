package com.sk.outlay.ui.overview

import androidx.lifecycle.ViewModel
import com.sk.outlay.data.Repository
import com.sk.outlay.data.models.TotalExpensesAmount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getTotalExpenses(): Flow<TotalExpensesAmount> {
        return repository.getTotalExpenses()
    }
}
