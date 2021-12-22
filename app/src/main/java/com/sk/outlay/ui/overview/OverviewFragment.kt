package com.sk.outlay.ui.overview

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
import com.google.accompanist.insets.ProvideWindowInsets
import com.sk.outlay.R
import com.sk.outlay.data.models.TotalExpensesAmount
import com.sk.outlay.theme.OutlayTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                OutlayTheme {
                    ProvideWindowInsets {
                        val totalExpensesAmount by viewModel.getTotalExpenses().collectAsState(
                            initial = TotalExpensesAmount(0f)
                        )
                        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
                        OverviewContent(
                            numberFormat.format(totalExpensesAmount.totalAmount),
                            ::navigateToTransactionScreen,
                            ::navigateToAccounts,
                            ::navigateToCategories,
                        )
                    }
                }
            }
        }
    }

    private fun navigateToTransactionScreen() {
        val bundle = bundleOf("repeatTransaction" to true)
        findNavController().navigate(R.id.action_overview_to_transaction, bundle)
    }

    private fun navigateToAccounts() {
        findNavController().navigate(R.id.action_overview_to_accounts)
    }

    private fun navigateToCategories() {
        findNavController().navigate(R.id.action_overview_to_categories)
    }
}