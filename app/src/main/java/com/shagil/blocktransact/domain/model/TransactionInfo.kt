package com.shagil.blocktransact.domain.model

data class TransactionInfo(
    val blockNumber:String,
    val blockHash: String,
    val from:String,
    val to:String,
    val transactionHash:String
)