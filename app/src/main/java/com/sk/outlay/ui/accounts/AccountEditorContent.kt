package com.sk.outlay.ui.accounts

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    accountType: AccountType,
    onAccountTypeChanged: (AccountType) -> Unit,
) {
    Text(text = "Type")
    Spacer(modifier = Modifier.height(8.dp))
    RadioButtonWithText(
        text = AccountType.Bank.value,
        selected = accountType == AccountType.Bank,
        onClick = { onAccountTypeChanged(AccountType.Bank) },
    )
    Spacer(modifier = Modifier.height(8.dp))
    RadioButtonWithText(
        text = AccountType.CreditCard.value,
        selected = accountType == AccountType.CreditCard,
        onClick = { onAccountTypeChanged(AccountType.CreditCard) },
    )
    Spacer(modifier = Modifier.height(8.dp))
    RadioButtonWithText(
        text = AccountType.EWallet.value,
        selected = accountType == AccountType.EWallet,
        onClick = { onAccountTypeChanged(AccountType.EWallet) },
    )
    Spacer(modifier = Modifier.height(8.dp))
    RadioButtonWithText(
        text = AccountType.Cash.value,
        selected = accountType == AccountType.Cash,
        onClick = { onAccountTypeChanged(AccountType.Cash) },
    )
    Spacer(modifier = Modifier.height(8.dp))
    RadioButtonWithText(
        text = AccountType.Other.value,
        selected = accountType == AccountType.Other,
        onClick = { onAccountTypeChanged(AccountType.Other) },
    )
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
