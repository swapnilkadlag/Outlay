package com.sk.outlay.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sk.outlay.R
import com.sk.outlay.data.entities.Account
import com.sk.outlay.ui.common.OutlayUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountsFragment : Fragment() {
    private val viewModel: AccountsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                OutlayUI {
                    val accounts by viewModel.getAccounts().collectAsState(initial = listOf())
                    AccountsContent(
                        accounts = accounts,
                        onAddClicked = ::add,
                        onBackPressed = ::back,
                        onAccountClicked = ::viewAccount,
                    )
                }
            }
        }
    }

    private fun back() {
        findNavController().popBackStack()
    }

    private fun add() {
        findNavController().navigate(R.id.action_accounts_to_account_editor)
    }

    private fun viewAccount(account: Account) {
        val bundle = bundleOf("accountId" to account.id.toString())
        findNavController().navigate(R.id.action_accounts_to_account_editor, bundle)
    }
}