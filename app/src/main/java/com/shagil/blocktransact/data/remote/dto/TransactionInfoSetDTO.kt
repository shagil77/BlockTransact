package com.shagil.blocktransact.data.remote.dto

data class TransactionInfoSetDTO(
    val blockNumber:String,
    val blockHash: String,
    val from:String,
    val to:String,
    val transactionHash:String,
    val gasUsed:String
)