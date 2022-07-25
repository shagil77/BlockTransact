package com.shagil.blocktransact.presentation.transaction_info

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
class TransactionsInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BlockTransactRepository
): ViewModel() {

    var state by mutableStateOf(TransactionsInfoState())

    init {
        viewModelScope.launch {
            val index=savedStateHandle.get<Int>("index") ?: return@launch
            state = state.copy(isLoading = true)

            val companyInfoResult = async {repository.getTransactionInfo(index)}

            when(val result = companyInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        transaction = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state=state.copy(
                        isLoading = false,
                        error = result.message,
                        transaction = null
                    )
                }
                else -> Unit
            }
        }
    }
}