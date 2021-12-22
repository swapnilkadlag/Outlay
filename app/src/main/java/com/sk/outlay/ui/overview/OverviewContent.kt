package com.sk.outlay.ui.overview

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Category
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sk.outlay.theme.OutlayTheme
import com.sk.outlay.theme.secondaryFont
import com.sk.outlay.ui.common.AddFab
import com.sk.outlay.ui.common.HorizontalSpace
import com.sk.outlay.ui.common.Toolbar
import com.sk.outlay.ui.common.VerticalSpace

@Composable
fun OverviewNavigationButton(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
            HorizontalSpace(space = 16.dp)
            Text(modifier = Modifier.weight(1f), text = title)
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
fun TotalExpenses(totalExpensesAmount: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f),
        verticalArrangement = Arrangement.Center,
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = "Total expenses")
        }
        Text(
            text = totalExpensesAmount,
            style = MaterialTheme.typography.h3,
            fontFamily = secondaryFont
        )
    }
}

@Composable
fun OverviewContent(
    totalExpensesAmount: String,
    onAddClicked: () -> Unit,
    onAccountsClick: () -> Unit,
    onCategoriesClick: () -> Unit,
) {
    Scaffold(
        topBar = { Toolbar(onBackPressed = {}, title = "Overview", false) },
        floatingActionButton = { AddFab(onAddClicked) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TotalExpenses(totalExpensesAmount)
            VerticalSpace(space = 16.dp)
            OverviewNavigationButton(
                title = "Accounts",
                icon = Icons.Default.AccountBalance,
                onClick = onAccountsClick
            )
            VerticalSpace(space = 16.dp)
            OverviewNavigationButton(
                title = "Categories",
                icon = Icons.Default.Category,
                onClick = onCategoriesClick,
            )
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun OverviewContentPreview() {
    OutlayTheme {
        OverviewContent(
            totalExpensesAmount = "$123,45.60",
            onAddClicked = {},
            onAccountsClick = {},
            onCategoriesClick = {},
        )
    }
}
