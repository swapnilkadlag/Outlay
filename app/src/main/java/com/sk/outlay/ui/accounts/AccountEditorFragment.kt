package com.sk.outlay.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sk.outlay.ui.common.OutlayUI
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AccountEditorFragment : Fragment() {
    @Inject
    lateinit var viewModelAssistedFactory: AccountEditorViewModel.Factory

    private val args: AccountEditorFragmentArgs by navArgs()

    private val viewModel: AccountEditorViewModel by viewModels {
        AccountEditorViewModel.provideFactory(
            viewModelAssistedFactory,
            args.accountId?.let { UUID.fromString(it) },
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
                OutlayUI {
                    AccountEditorContent(
                        title = viewModel.title,
                        accountName = viewModel.accountName,
                        onAccountNameChanged = { value -> viewModel.accountName = value },
                        accountType = viewModel.accountType,
                        onAccountTypeChanged = { value -> viewModel.accountType = value },
                        accountDetails = viewModel.accountDetails,
                        onAccountDetailsChanged = { value -> viewModel.accountDetails = value },
                        onBackPressed = ::back,
                        onSaveClicked = ::save,
                    )
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadInitialData()
    }

    private fun back() {
        findNavController().popBackStack()
    }

    private fun save() {
        viewModel.saveAccount()
        back()
    }
}