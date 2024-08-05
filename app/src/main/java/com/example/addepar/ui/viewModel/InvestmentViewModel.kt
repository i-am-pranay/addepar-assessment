package com.example.addepar.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addepar.common.utils.CustomDispatchers
import com.example.addepar.common.utils.Result
import com.example.addepar.domain.models.Investment
import com.example.addepar.domain.useCase.GetInvestmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvestmentViewModel @Inject constructor(
    private val getInvestmentUseCase: GetInvestmentUseCase,
    private val customDispatchers: CustomDispatchers,
): ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var _investmentList = MutableStateFlow(emptyList<Investment>())
    val investmentList = _investmentList.asStateFlow()

    fun getInvestment() {

        _isLoading.update { true }

        viewModelScope.launch(customDispatchers.Main) {

            getInvestmentUseCase.invoke()
                .flowOn(customDispatchers.IO)
                .onEach { result ->
                    when (result) {
                        is Result.Error -> { _isLoading.update { false }}
                        is Result.Loading -> { _isLoading.update { result.isLoading }}
                        is Result.Success -> {
                            result.data?.let { listOfInvestment ->
                                _investmentList.update {
                                    listOfInvestment
                                }

                                _isLoading.update {
                                    false
                                }
                            }
                        }
                    }
                }.collect()
        }
    }
}