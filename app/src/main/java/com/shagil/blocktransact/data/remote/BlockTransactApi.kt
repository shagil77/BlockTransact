package com.shagil.blocktransact.data.remote

import android.util.Log
import com.shagil.blocktransact.contractfiles.SimpleStorage
import com.shagil.blocktransact.data.remote.dto.TransactionInfoDTO
import com.shagil.blocktransact.data.remote.dto.TransactionInfoSetDTO
import com.shagil.blocktransact.domain.model.TransactionsListings
import com.shagil.blocktransact.secretfiles.API_KEY
import com.shagil.blocktransact.secretfiles.CONTRACT_ADDRESS
import com.shagil.blocktransact.secretfiles.HTTP_URL
import com.shagil.blocktransact.secretfiles.PRIVATE_KEY
import kotlinx.coroutines.*
import org.web3j.abi.datatypes.generated.Uint256
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import java.math.BigInteger
import java.util.concurrent.CompletableFuture

/*
interface BlockTransactApi {
    suspend fun getNumberOfTransactions():Uint256 {

    }

    suspend fun getTransactionListings():List<TransactionsListings>

    suspend fun getTransactionInfo(index: Int):TransactionInfoDTO

    suspend fun setTransaction(
        index:Uint256
    ):List<Uint256>
}*/

class BlockTransactApi {
    private val TAG = "BlockTransactApi"
    private val web3j = Web3j.build(HttpService(HTTP_URL+ API_KEY))
    private val gasLimit: BigInteger = BigInteger.valueOf(20_000_000_000L)
    private val gasPrice: BigInteger = BigInteger.valueOf(4300000)

    val credentials = Credentials.create(PRIVATE_KEY)
    private val simpleStorage = SimpleStorage.load(CONTRACT_ADDRESS, web3j, credentials, gasLimit, gasPrice)

    suspend fun getNumberOfTransactions():Int = withContext(Dispatchers.IO) {
        val response: CompletableFuture<Uint256>? = simpleStorage.size().sendAsync()
        val answer:BigInteger? = response?.get()?.value
        Log.e(TAG, "getNumberOfTransactions + ${answer?.toInt()}")
        answer?.toInt()!!
    }

    suspend fun getTransactionListings():ArrayList<TransactionsListings> = withContext(Dispatchers.IO) {
        val N=async { getNumberOfTransactions() }
        val start:Int = 0
        val end:Int = N.await()
        var answer:ArrayList<TransactionsListings> = ArrayList()

        for(i in start until end) {
            val response:CompletableFuture<Uint256>? = simpleStorage.get(BigInteger.valueOf(i.toLong())).sendAsync()
            val amount:Int? = response?.get()?.value?.toInt()

            val responseTrans:CompletableFuture<TransactionReceipt>? = simpleStorage.getFuncTransReceipt(BigInteger.valueOf(
                i.toLong()
            )).sendAsync()
            val transactionHash:String? = responseTrans?.get()?.transactionHash?.toString()
            Log.e(TAG, "getTransactionListings + ${transactionHash.toString()}")
            answer.add(
                TransactionsListings(
                index = i,
                amount = amount.toString(),
                transactionHash = transactionHash.toString()
            )
            )
        }

        answer
    }

    suspend fun getTransactionInfo(index:Int):TransactionInfoDTO = withContext(Dispatchers.IO) {
        val response:CompletableFuture<TransactionReceipt>? = simpleStorage.getFuncTransReceipt(BigInteger.valueOf(
            index.toLong()
        )).sendAsync()
        val blockNumber:String? = response?.get()?.blockNumber?.toString()
        val blockHash: String? = response?.get()?.blockHash?.toString()
        val from:String? = response?.get()?.from?.toString()
        val to:String? = response?.get()?.to?.toString()
        val transactionHash:String? = response?.get()?.transactionHash?.toString()

        TransactionInfoDTO(
            blockNumber = blockNumber.toString(),
            blockHash = blockHash.toString(),
            from = from.toString(),
            to = to.toString(),
            transactionHash = transactionHash.toString()
        )
    }

    suspend fun setTransaction(amount:Int):TransactionInfoSetDTO = withContext(Dispatchers.IO) {
        val transGet:CompletableFuture<TransactionReceipt>?= simpleStorage.set(BigInteger.valueOf(amount.toLong())).sendAsync()
        val result = transGet?.get()
        TransactionInfoSetDTO(
            blockNumber = result?.blockNumber?.toString().toString(),
            blockHash = result?.blockHash?.toString().toString(),
            from = result?.from?.toString().toString(),
            to = result?.to?.toString().toString(),
            transactionHash = result?.transactionHash?.toString().toString(),
            gasUsed = result?.gasUsed?.toString().toString()
        )
    }
    /*val transGet: CompletableFuture<TransactionReceipt>? = simpleStorage.set(BigInteger.valueOf(7373)).sendAsync()
    val result = "Successful transaction. Gas used: ${transGet?.get()?.blockNumber} ${transGet?.get()?.gasUsed}."
    Log.e(TAG, result)*/
}
