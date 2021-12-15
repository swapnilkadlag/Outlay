package com.sk.outlay.ui.accounts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sk.outlay.data.Repository
import com.sk.outlay.data.entities.Account
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

class AccountEditorViewModel @AssistedInject constructor(
    private val repository: Repository,
    @Named(APP_COROUTINE_SCOPE_QUALIFIER) val appScope: CoroutineScope,
    @Named(IO_DISPATCHER_QUALIFIER) val dispatcher: CoroutineDispatcher,
    @Assisted private val accountId: UUID?,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(accountId: UUID?): AccountEditorViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            accountId: UUID?,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(accountId) as T
            }
        }
    }

    private var account: Account? = null

    var accountName by mutableStateOf("")

    var accountType by mutableStateOf(com.sk.outlay.data.enums.AccountType.Bank)

    var accountDetails by mutableStateOf("")

    private val isNewAccount get() = accountId == null

    val title get() = if (isNewAccount) "Add Account" else "Edit Account"

    fun loadInitialData() {
        if (accountId != null) {
            loadAccount(accountId)
        }
    }

    private fun loadAccount(accountId: UUID) {
        viewModelScope.launch(dispatcher) {
            account = repository.getAccount(accountId)
            accountName = account!!.name
            accountType = account!!.type
            accountDetails = account!!.details ?: ""
        }
    }

    fun saveAccount() {
        appScope.launch(dispatcher) {
            if (isNewAccount) {
                repository.createAccount(accountName, accountType, accountDetails)
            } else {
                val updatedAccount = account!!.copy(
                    name = accountName,
                    type = accountType,
                    details = accountDetails,
                )
                repository.updateAccount(updatedAccount)
            }
        }
    }
}
