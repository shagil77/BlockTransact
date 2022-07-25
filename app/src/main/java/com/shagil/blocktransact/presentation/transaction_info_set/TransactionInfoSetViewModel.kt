package com.shagil.blocktransact.presentation.transaction_info_set


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.shagil.blocktransact.domain.repository.BlockTransactRepository

import com.shagil.blocktransact.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionInfoSetViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BlockTransactRepository
): ViewModel() {

    var state by mutableStateOf(TransactionInfoSetState())

    fun onEvent(event: TransactionInfoSetEvent) {
        when(event) {
            is TransactionInfoSetEvent.Clicked -> {
                if(state.searchQuery.isNotBlank()) {
                    setTransactions(amount = state.searchQuery.toInt())
                }
            }
            is TransactionInfoSetEvent.OnSearchQueryChange -> {
                state=state.copy(searchQuery = event.query)
            }
        }
    }

    private fun setTransactions(
        amount:Int
    ) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val companyInfoSet = async { repository.setTransaction(amount) }
            when(val result = companyInfoSet.await()) {
                is Resource.Success -> {
                    result.data?.let {
                        state = state.copy(
                            transaction = it,
                            isLoading = false,
                            canDisplayTransactionInfo = true,
                            amount = amount.toString(),
                            searchQuery = ""
                        )
                    }
                }
                is Resource.Error -> {
                    state=state.copy(
                        isLoading = false,
                        error = result.message,
                        transaction = null
                    )
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = result.isLoading)
                }
            }
        }
    }
}