package com.sk.outlay.ui.categories

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
class CategoryEditorFragment : Fragment() {
    @Inject
    lateinit var viewModelAssistedFactory: CategoryEditorViewModel.Factory

    private val args: CategoryEditorFragmentArgs by navArgs()

    private val viewModel: CategoryEditorViewModel by viewModels {
        CategoryEditorViewModel.provideFactory(
            viewModelAssistedFactory,
            args.categoryId?.let { UUID.fromString(it) },
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
                    CategoryEditorContent(
                        title = viewModel.title,
                        categoryName = viewModel.categoryName,
                        onCategoryNameChanged = { value -> viewModel.categoryName = value },
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
        viewModel.saveCategory()
        back()
    }
}