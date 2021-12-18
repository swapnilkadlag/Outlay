package com.sk.outlay.ui.accounts

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.theme.OutlayTheme
import com.sk.outlay.ui.common.RadioButtonWithText
import com.sk.outlay.ui.common.SaveFab
import com.sk.outlay.ui.common.Toolbar

@Composable
fun AccountName(
    accountName: String,
    onAccountNameChanged: (String) -> Unit,
) {
    Text(text = "Name")
    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = accountName,
        onValueChange = onAccountNameChanged
    )
}

@Composable
fun AccountType(
    selectedAccountType: AccountType,
    onAccountTypeChanged: (AccountType) -> Unit,
) {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    val items = AccountType.values()
    Text(text = "Type", Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(8.dp))
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface, MaterialTheme.shapes.small)
            .clickable { setExpanded(true) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = selectedAccountType.value,
                    Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    style = MaterialTheme.typography.h6,
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { setExpanded(false) },
                modifier = Modifier.background(MaterialTheme.colors.surface)
            ) {
                items.forEach { at ->
                    DropdownMenuItem(onClick = {
                        onAccountTypeChanged(at)
                        setExpanded(false)
                    }) {
                        Text(text = at.value)
                    }
                }
            }
        }
    }
}

@Composable
fun AccountDetails(
    accountDetails: String,
    onAccountDetailsChanged: (String) -> Unit,
) {
    Text(text = "Details")
    Spacer(modifier = Modifier.height(8.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = accountDetails,
        onValueChange = onAccountDetailsChanged,
        maxLines = 2,
    )
}

@Composable
fun AccountEditorContent(
    title: String,
    accountName: String,
    onAccountNameChanged: (String) -> Unit,
    accountType: AccountType,
    onAccountTypeChanged: (AccountType) -> Unit,
    accountDetails: String,
    onAccountDetailsChanged: (String) -> Unit,
    onBackPressed: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    Scaffold(
        topBar = { Toolbar(onBackPressed = onBackPressed, title = title) },
        floatingActionButton = { SaveFab(onSaveClicked) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AccountName(accountName, onAccountNameChanged)
            Spacer(modifier = Modifier.height(32.dp))
            AccountType(accountType, onAccountTypeChanged)
            Spacer(modifier = Modifier.height(32.dp))
            AccountDetails(accountDetails, onAccountDetailsChanged)
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun AccountEditorContentPreview() {
    OutlayTheme {
        AccountEditorContent(
            title = "Add Account",
            accountName = "State Bank of India",
            onAccountNameChanged = {},
            accountType = AccountType.Bank,
            onAccountTypeChanged = {},
            accountDetails = "Savings Account",
            onAccountDetailsChanged = {},
            onBackPressed = {},
            onSaveClicked = {},
        )
    }
}
