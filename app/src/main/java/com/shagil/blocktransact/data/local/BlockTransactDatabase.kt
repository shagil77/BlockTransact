package com.shagil.blocktransact.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TransactionsListingsEntity::class],
    version = 1
)
abstract class BlockTransactDatabase:RoomDatabase() {
    abstract val blockTransactDAO:BlockTransactDAO
}