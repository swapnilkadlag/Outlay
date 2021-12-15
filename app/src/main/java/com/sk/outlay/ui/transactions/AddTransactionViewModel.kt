package com.sk.outlay.ui.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sk.outlay.data.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.util.*

class AddTransactionViewModel @AssistedInject constructor(
    private val repository: Repository,
    @Assisted private val repeatTransaction: Boolean,
    @Assisted private val transactionId: UUID?,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(
            transactionId: UUID?,
            repeatTransaction: Boolean,
        ): AddTransactionViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: Factory,
            transactionId: UUID?,
            repeatTransaction: Boolean,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(transactionId, repeatTransaction) as T
            }
        }
    }
}