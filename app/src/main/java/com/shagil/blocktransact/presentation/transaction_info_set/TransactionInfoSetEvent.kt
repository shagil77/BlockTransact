package com.shagil.blocktransact.presentation.transaction_info_set

sealed class TransactionInfoSetEvent {
    object Clicked:TransactionInfoSetEvent()
    data class OnSearchQueryChange(val query:String): TransactionInfoSetEvent()
}