package com.shagil.blocktransact.data.local

import androidx.room.*

@Dao
interface BlockTransactDAO {
    // 1. insert transactions
    // 2. search transaction
    // 3. delete local db
    @Insert
    suspend fun insertBlockTransaction(
        transactionsListingsEntity: List<TransactionsListingsEntity>
    )

    @Query("DELETE FROM transactionslistingsentity")
    suspend fun clearLocalDB()

    @Query(
        """
            SELECT *
            FROM transactionslistingsentity
            WHERE LOWER(amount) LIKE "%" || LOWER(:query) || "%" OR 
                LOWER(transactionHash) LIKE "%" || LOWER(:query) || "%"
        """
    )
    suspend fun searchTransactionListings(query: String) : List<TransactionsListingsEntity>
}