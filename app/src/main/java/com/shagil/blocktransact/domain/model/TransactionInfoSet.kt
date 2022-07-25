package com.shagil.blocktransact.domain.model

data class TransactionInfoSet(
    val blockNumber:String,
    val blockHash: String,
    val from:String,
    val to:String,
    val transactionHash:String,
    val gasUsed:String
)