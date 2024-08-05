package com.example.addepar.ui.viewModel

import app.cash.turbine.test
import com.example.addepar.common.utils.CustomDispatchers
import com.example.addepar.common.utils.Result
import com.example.addepar.common.utils.TestCoroutineRule
import com.example.addepar.common.utils.TestDispatchers
import com.example.addepar.domain.models.Investment
import com.example.addepar.domain.useCase.GetInvestmentUseCase
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InvestmentViewModelTest {
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var getInvestmentUseCase: GetInvestmentUseCase
    private lateinit var customDispatchers: CustomDispatchers
    private lateinit var sut: InvestmentViewModel

    @Before
    fun setUp() {
        getInvestmentUseCase = mock()
        customDispatchers = TestDispatchers()

        sut = InvestmentViewModel(getInvestmentUseCase, customDispatchers)
    }

    @Test
    fun `getInvestment(), expecting non empty array`() = runBlocking {
        whenever(getInvestmentUseCase.invoke()).thenReturn(flow {
            emit(Result.Success(data = listOf(Investment.preview)))
        })
        sut.getInvestment()

        sut.investmentList.test {
            val firstEmission = awaitItem()
            assertThat(firstEmission.size).isEqualTo(1)
            cancelAndIgnoreRemainingEvents()
        }


    }

    @Test
    fun `getInvestment(), expecting loading to reflect`() = runBlocking {
        whenever(getInvestmentUseCase.invoke()).thenReturn(flow {
            emit(Result.Loading(isLoading = true))
            emit(Result.Loading(isLoading = false))
        })

          sut.isLoading.test {
              sut.getInvestment()
              val firstEmission = awaitItem()
              assertThat(firstEmission).isEqualTo(false)

              val secondEmission = awaitItem()
              assertThat(secondEmission).isEqualTo(true)


              val thirdEmission = awaitItem()
              assertThat(thirdEmission).isEqualTo(false)
              cancelAndIgnoreRemainingEvents()
          }


    }




    @After
    fun tearDown() {
    }
}