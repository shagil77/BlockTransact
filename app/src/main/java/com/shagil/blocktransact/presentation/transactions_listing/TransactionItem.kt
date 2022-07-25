package com.shagil.blocktransact.presentation.transactions_listing

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shagil.blocktransact.domain.model.TransactionsListings

@Composable
fun TransactionItem(
    transaction:TransactionsListings,
    modifier:Modifier=Modifier
) {
    Row(
        modifier=modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier=Modifier.weight(1f),
        ) {
            Row(
                modifier=Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Rs. ${transaction.amount.toString()}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = transaction.type,
//                    fontWeight = FontWeight.Light,
//                    color = MaterialTheme.colors.onBackground
//                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Transaction Hash: ${transaction.transactionHash}",
                fontStyle = FontStyle.Italic,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}