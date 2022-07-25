package com.shagil.blocktransact.domain.repository

import com.shagil.blocktransact.domain.model.TransactionInfo
import com.shagil.blocktransact.domain.model.TransactionInfoSet
import com.shagil.blocktransact.domain.model.TransactionsListings
import com.shagil.blocktransact.util.Resource
import kotlinx.coroutines.flow.Flow

interface BlockTransactRepository {
    suspend fun getTransactions(
        fetchFromRemote:Boolean,
        query:String
    ): Flow<Resource<List<TransactionsListings>>>

    suspend fun getTransactionInfo(
        index: Int
    ):Resource<TransactionInfo>

    suspend fun setTransaction(
        amount: Int
    ):Resource<TransactionInfoSet>
}
/*
* 1. getTransactions
* 2.
* */
/*suspend fun getCompanyListings(
        fetchFromRemote:Boolean,
        query:String
    ):Flow<Resource<List<CompanyListings>>>

    suspend fun getIntradayInfo(
        symbol:String
    ):Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ):Resource<CompanyInfo>*/