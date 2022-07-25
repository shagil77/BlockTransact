package com.shagil.blocktransact.presentation.transaction_info_set

import com.shagil.blocktransact.domain.model.TransactionInfoSet

data class TransactionInfoSetState(
    val transaction: TransactionInfoSet?=null,
    val isLoading:Boolean=false,
    val error:String?=null,
    val searchQuery:String="",
    val canDisplayTransactionInfo:Boolean=false,
    val amount:String=""
)