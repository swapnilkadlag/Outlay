package com.sk.outlay.ui.categories

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
import com.sk.outlay.data.entities.Category
import com.sk.outlay.ui.accounts.AccountsContent
import com.sk.outlay.ui.accounts.AccountsViewModel
import com.sk.outlay.ui.common.OutlayUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private val viewModel: CategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                OutlayUI {
                    val categories by viewModel.getCategories().collectAsState(initial = listOf())
                    CategoriesContent(
                        categories = categories,
                        onAddClicked = ::add,
                        onBackPressed = ::back,
                        onCategoryClicked = ::viewCategory,
                    )
                }
            }
        }
    }

    private fun back() {
        findNavController().popBackStack()
    }

    private fun add() {
        findNavController().navigate(R.id.action_categories_to_category_editor)
    }

    private fun viewCategory(category: Category) {
        val bundle = bundleOf("categoryId" to category.id.toString())
        findNavController().navigate(R.id.action_categories_to_category_editor, bundle)
    }
}