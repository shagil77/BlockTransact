package com.shagil.blocktransact.presentation.transactions_listing

sealed class TransactionsListingsEvent {
    object Refresh :TransactionsListingsEvent()
    data class OnSearchQueryChange(val query:String): TransactionsListingsEvent()
}