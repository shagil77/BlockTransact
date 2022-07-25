package com.shagil.blocktransact.data.mapper

import com.shagil.blocktransact.data.local.TransactionsListingsEntity
import com.shagil.blocktransact.data.remote.dto.TransactionInfoDTO
import com.shagil.blocktransact.data.remote.dto.TransactionInfoSetDTO
import com.shagil.blocktransact.domain.model.TransactionInfo
import com.shagil.blocktransact.domain.model.TransactionInfoSet
import com.shagil.blocktransact.domain.model.TransactionsListings

fun TransactionsListingsEntity.toTransactionsListings():TransactionsListings {
    return TransactionsListings(
        index = index,
        amount = amount.toString(),
        transactionHash = transactionHash
    )
}

fun TransactionsListings.toTransactionsListingsEntity():TransactionsListingsEntity {
    return TransactionsListingsEntity(
        index=index,
        amount = amount.toString(),
        transactionHash = transactionHash
    )
}

fun TransactionInfoDTO.toTransactionInfo() : TransactionInfo {
    return TransactionInfo(
        blockNumber = blockNumber,
        blockHash = blockHash,
        from = from,
        to = to,
        transactionHash = transactionHash
    )
}

fun TransactionInfoSetDTO.toTransactionInfoSet() : TransactionInfoSet {
    return TransactionInfoSet(
        blockNumber = blockNumber,
        blockHash = blockHash,
        from = from,
        to = to,
        transactionHash = transactionHash,
        gasUsed = gasUsed
    )
}