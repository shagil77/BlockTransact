package com.shagil.blocktransact.data.repository

import com.shagil.blocktransact.data.local.BlockTransactDatabase
import com.shagil.blocktransact.data.mapper.toTransactionInfo
import com.shagil.blocktransact.data.mapper.toTransactionInfoSet
import com.shagil.blocktransact.data.mapper.toTransactionsListings
import com.shagil.blocktransact.data.mapper.toTransactionsListingsEntity
import com.shagil.blocktransact.data.remote.BlockTransactApi
import com.shagil.blocktransact.domain.model.TransactionInfo
import com.shagil.blocktransact.domain.model.TransactionInfoSet
import com.shagil.blocktransact.domain.model.TransactionsListings
import com.shagil.blocktransact.domain.repository.BlockTransactRepository
import com.shagil.blocktransact.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlockTransactRepositoryImpl @Inject constructor(
    /*private val api:BlockTransactApi,*/
    private val db:BlockTransactDatabase
):BlockTransactRepository {
    private val dao = db.blockTransactDAO

    private val api:BlockTransactApi = BlockTransactApi()

    override suspend fun getTransactions(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<TransactionsListings>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchTransactionListings(query)
            emit(Resource.Success(
                data = localListings.map {
                    it.toTransactionsListings()
                }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getTransactionListings()
                response
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find data!"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't find data!"))
                null
            }

            remoteListings?.let { listings->
                dao.clearLocalDB()
                dao.insertBlockTransaction(
                    listings.map { it.toTransactionsListingsEntity() })
                emit(Resource.Success(
                    data = dao
                        .searchTransactionListings("")
                        .map { it.toTransactionsListings() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getTransactionInfo(index: Int): Resource<TransactionInfo> {
        return try {
            val result = api.getTransactionInfo(index)
            Resource.Success(result.toTransactionInfo())
        } catch (e:IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load transactions info!"
            )
        } catch (e:HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load transactions info!"
            )
        }
    }

    override suspend fun setTransaction(amount: Int): Resource<TransactionInfoSet> {
        return try {
            val result = api.setTransaction(amount)
            Resource.Success(result.toTransactionInfoSet())
        } catch (e:IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load transactions info!"
            )
        } catch (e:HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load transactions info!"
            )
        }
    }

}