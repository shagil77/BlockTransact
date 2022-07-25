package com.shagil.blocktransact.data.remote.dto

data class TransactionInfoDTO(
    val blockNumber:String,
    val blockHash: String,
    val from:String,
    val to:String,
    val transactionHash:String
)
