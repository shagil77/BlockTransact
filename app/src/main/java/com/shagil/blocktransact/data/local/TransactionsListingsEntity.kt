package com.shagil.blocktransact.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionsListingsEntity(
    val index:Int,
    val amount:String,

    val transactionHash:String,
    @PrimaryKey val id:Int?=null
)
