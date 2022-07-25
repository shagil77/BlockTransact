package com.shagil.blocktransact.presentation.transaction_info

import com.shagil.blocktransact.domain.model.TransactionInfo

data class TransactionsInfoState(
    val transaction:TransactionInfo?=null,
    val isLoading:Boolean=false,
    val error:String?=null
)