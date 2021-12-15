package com.sk.outlay.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.accompanist.insets.ProvideWindowInsets
import com.sk.outlay.theme.OutlayTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    @Inject
    lateinit var viewModelAssistedFactory: AddTransactionViewModel.Factory

    private val args: AddTransactionFragmentArgs by navArgs()

    private val viewModel: AddTransactionViewModel by viewModels {
        AddTransactionViewModel.provideFactory(
            viewModelAssistedFactory,
            args.transactionId?.let { UUID.fromString(it) },
            args.repeatTransaction,
        )
    }

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
                        AddTransactionUI("")
                    }
                }
            }
        }
    }
}