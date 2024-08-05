package com.example.addepar.domain.useCase

import app.cash.turbine.test
import com.example.addepar.common.utils.Result
import com.example.addepar.data.repository.InvestmentRepoImpl
import com.example.addepar.domain.models.Investment
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test

class GetInvestmentUseCaseTest {

    private lateinit var investmentRepoImpl: InvestmentRepoImpl
    private lateinit var getInvestmentUseCase: GetInvestmentUseCase

    @Before
    fun setUp() {
        investmentRepoImpl = mock()
        getInvestmentUseCase = GetInvestmentUseCase(investmentRepoImpl)
    }

    @Test
    fun `get success result`() = runBlocking {
        whenever(investmentRepoImpl.getInvestment()).thenReturn(flow {
            emit(Result.Loading(isLoading = true))
            emit(Result.Loading(isLoading = false))
            emit(Result.Success(data = emptyList()))

        })
        val flow = getInvestmentUseCase.invoke()


        flow.test {
            val initialLoading = awaitItem()
            assertThat((initialLoading as Result.Loading).isLoading).isEqualTo(true)

            val endLoading = awaitItem()
            assertThat((endLoading as Result.Loading).isLoading).isEqualTo(false)

            val investmentList = awaitItem()
            assertThat((investmentList as Result.Success).data).isEqualTo(emptyList<Investment>())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
    }
}