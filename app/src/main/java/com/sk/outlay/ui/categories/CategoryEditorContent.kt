package com.sk.outlay.ui.categories

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sk.outlay.theme.OutlayTheme
import com.sk.outlay.ui.common.SaveFab
import com.sk.outlay.ui.common.Toolbar

@Composable
fun CategoryName(
    categoryName: String,
    onCategoryNameChanged: (String) -> Unit,
) {
    Text(text = "Name")
    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = categoryName,
        onValueChange = onCategoryNameChanged,
    )
}

@Composable
fun CategoryEditorContent(
    title: String,
    categoryName: String,
    onCategoryNameChanged: (String) -> Unit,
    onBackPressed: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    Scaffold(
        topBar = { Toolbar(onBackPressed = onBackPressed, title = title) },
        floatingActionButton = { SaveFab(onSaveClicked) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CategoryName(categoryName, onCategoryNameChanged)
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CategoryEditorContentPreview() {
    OutlayTheme {
        CategoryEditorContent(
            title = "Add Category",
            categoryName = "Food",
            onCategoryNameChanged = {},
            onBackPressed = {},
            onSaveClicked = {},
        )
    }
}
