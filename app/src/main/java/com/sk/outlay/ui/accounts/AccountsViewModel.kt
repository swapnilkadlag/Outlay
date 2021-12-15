package com.sk.outlay.ui.accounts

import androidx.lifecycle.ViewModel
import com.sk.outlay.data.Repository
import com.sk.outlay.data.entities.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    fun getAccounts(): Flow<List<Account>> {
        return repository.getAccounts()
    }
}