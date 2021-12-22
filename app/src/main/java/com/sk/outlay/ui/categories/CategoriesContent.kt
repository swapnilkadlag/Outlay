package com.sk.outlay.ui.categories

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sk.outlay.data.entities.Category
import com.sk.outlay.theme.outlayColor
import com.sk.outlay.ui.common.AddFab
import com.sk.outlay.ui.common.HorizontalSpace
import com.sk.outlay.ui.common.OutlayUI
import com.sk.outlay.ui.common.Toolbar
import com.sk.outlay.utils.getColorForString
import java.util.*


@Composable
fun CategoryItem(category: Category, onCategoryClicked: (Category) -> Unit) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(outlayColor(outlayColor = category.color))
                .size(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = category.name.first().toString(),
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
        }
        HorizontalSpace(space = 16.dp)
        Column(
            Modifier
                .fillMaxWidth()
                .clickable { onCategoryClicked(category) }) {
            Text(modifier = Modifier.fillMaxWidth(), text = category.name)
        }
    }
}

@Composable
fun CategoryList(categories: List<Category>, onCategoryClicked: (Category) -> Unit) {
    LazyColumn {
        items(categories) { category ->
            CategoryItem(category, onCategoryClicked)
        }
    }
}

@Composable
fun CategoriesContent(
    categories: List<Category>,
    onAddClicked: () -> Unit,
    onBackPressed: () -> Unit,
    onCategoryClicked: (Category) -> Unit,
) {
    Scaffold(
        topBar = { Toolbar(onBackPressed = onBackPressed, title = "Categories") },
        floatingActionButton = { AddFab(onAddClicked) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            CategoryList(categories = categories, onCategoryClicked = onCategoryClicked)
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CategoriesContentPreview() {
    OutlayUI {
        CategoriesContent(
            categories = listOf(
                Category(
                    UUID.randomUUID(),
                    "Food",
                    getColorForString("Food"),
                ),
                Category(
                    UUID.randomUUID(),
                    "Other",
                    getColorForString("Other"),
                ),
                Category(
                    UUID.randomUUID(),
                    "Essentials",
                    getColorForString("Essentials"),
                ),
            ),
            onAddClicked = {},
            onBackPressed = {},
            onCategoryClicked = {},
        )
    }
}