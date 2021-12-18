package com.sk.outlay.ui.accounts

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sk.outlay.data.entities.Account
import com.sk.outlay.data.enums.AccountType
import com.sk.outlay.theme.outlayColor
import com.sk.outlay.ui.common.AddFab
import com.sk.outlay.ui.common.HorizontalSpace
import com.sk.outlay.ui.common.OutlayUI
import com.sk.outlay.ui.common.Toolbar
import com.sk.outlay.utils.getColorForString
import java.util.*

@Composable
fun AccountItem(account: Account, onAccountClicked: (Account) -> Unit) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(outlayColor(outlayColor = account.color))
                .size(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = account.name.first().toString(),
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center
            )
        }
        HorizontalSpace(space = 16.dp)
        Column(
            Modifier
                .fillMaxWidth()
                .clickable { onAccountClicked(account) }) {
            Text(modifier = Modifier.fillMaxWidth(), text = account.name)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = account.type.value,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Composable
fun AccountList(accounts: List<Account>, onAccountClicked: (Account) -> Unit) {
    LazyColumn {
        items(accounts) { account ->
            AccountItem(account, onAccountClicked)
        }
    }
}

@Composable
fun AccountsContent(
    accounts: List<Account>,
    onAddClicked: () -> Unit,
    onBackPressed: () -> Unit,
    onAccountClicked: (Account) -> Unit,
) {
    Scaffold(
        topBar = { Toolbar(onBackPressed = onBackPressed, title = "Accounts") },
        floatingActionButton = { AddFab(onAddClicked) }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AccountList(accounts = accounts, onAccountClicked = onAccountClicked)
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AccountsContentPreview() {
    OutlayUI {
        AccountsContent(
            accounts = listOf(
                Account(
                    UUID.randomUUID(),
                    "State bank of India",
                    AccountType.Bank,
                    "",
                    getColorForString("State bank of India"),
                ),
                Account(
                    UUID.randomUUID(),
                    "Paytm Wallet",
                    AccountType.EWallet,
                    "",
                    getColorForString("Paytm Wallet"),
                ),
                Account(
                    UUID.randomUUID(),
                    "HDFC Credit card",
                    AccountType.CreditCard,
                    "",
                    getColorForString("HDFC Credit card"),
                ),
                Account(
                    UUID.randomUUID(),
                    "Cash",
                    AccountType.Cash,
                    "",
                    getColorForString("Cash")
                ),
            ),
            onAddClicked = {},
            onBackPressed = {},
            onAccountClicked = {},
        )
    }
}
