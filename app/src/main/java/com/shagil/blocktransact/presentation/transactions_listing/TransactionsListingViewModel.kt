package com.shagil.blocktransact.presentation.transactions_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shagil.blocktransact.domain.repository.BlockTransactRepository
import com.shagil.blocktransact.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsListingViewModel @Inject constructor(
    private val repository: BlockTransactRepository
):ViewModel() {
    var state by mutableStateOf(TransactionsListingState())

    private var searchJob: Job?=null
    init {
        getTransactionsListings()
    }

    fun onEvent(event: TransactionsListingsEvent) {
        when(event) {
            is TransactionsListingsEvent.Refresh -> {
                getTransactionsListings(fetchFromRemote = true)
            }
            is TransactionsListingsEvent.OnSearchQueryChange -> {
                state=state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob=viewModelScope.launch {
                    delay(500L)
                    getTransactionsListings()
                }
            }
        }
    }

    private fun getTransactionsListings(
        query:String=state.searchQuery.lowercase(),
        fetchFromRemote:Boolean=false
    ) {
        viewModelScope.launch {
            repository
                .getTransactions(fetchFromRemote,query)
                .collect{ result->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings->
                                state = state.copy(
                                    transactions = listings
                                )
                            }
                        }
                        is Resource.Error -> {
                            // Do on your own
                            Unit
                        }
                        is Resource.Loading -> {
                            state=state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}