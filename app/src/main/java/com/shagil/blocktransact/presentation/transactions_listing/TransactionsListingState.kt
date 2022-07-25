package com.shagil.blocktransact.presentation.transactions_listing

import com.shagil.blocktransact.domain.model.TransactionsListings

data class TransactionsListingState(
    val transactions:List<TransactionsListings> = emptyList(),
    val isLoading:Boolean = false,
    val isRefreshing:Boolean=false,
    val searchQuery:String=""
)
