package com.example.addepar.data.repository

import android.content.Context
import android.content.res.Resources
import app.cash.turbine.test
import com.example.addepar.common.utils.Result
import com.example.addepar.domain.models.Investment
import com.example.addepar.domain.models.InvestmentResponse
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doThrow
import org.junit.After
import org.junit.Before
import org.assertj.core.api.Assertions.assertThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import java.io.ByteArrayInputStream
import java.io.IOException


class InvestmentRepoImplTest {

    private lateinit var context: Context
    private lateinit var resources: Resources
    private lateinit var sut: InvestmentRepoImpl

    @Before
    fun setUp() {
        context = mock()
        resources = mock()
        sut = InvestmentRepoImpl(context = context)
    }

    @Test
    fun `test getInvestment for success flow, expected non empty result`(): Unit = runBlocking {
        whenever(context.resources).thenReturn(resources)
        whenever(context.resources.openRawResource(anyInt())).thenReturn(
            ByteArrayInputStream("{ \"investments\": [] }".toByteArray())
        )

        val response = sut.getInvestment()

        response.test {
            val first = awaitItem()
            assert(first is Result.Loading)
            assert((first as Result.Loading).isLoading)

            val second = awaitItem()
            assert(second is Result.Loading)
            assert(!(second as Result.Loading).isLoading)

            val third = awaitItem()
            assert(third is Result.Success)
            assertThat((third as Result.Success).data).isEqualTo(emptyList<Investment>())

            val fourth = awaitItem()
            assert(fourth is Result.Loading)
            assert(!(fourth as Result.Loading).isLoading)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `unable to read file, expect error`() = runBlocking {
        whenever(context.resources).thenReturn(resources)
        whenever(context.resources.openRawResource(anyInt())).then {
          throw  IOException("Unable to read file")
        }

        val flow = sut.getInvestment()

        flow.test {
            val first = awaitItem()
            assert(first is Result.Loading)
            assert((first as Result.Loading).isLoading)

            val second = awaitItem()
            assert(second is Result.Error)
            assertThat((second as Result.Error).message).isEqualTo("Error in reading file: java.io.IOException: Unable to read file")

            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `unable to parse, expect error`() = runBlocking {
        whenever(context.resources).thenReturn(resources)
        whenever(context.resources.openRawResource(anyInt())).thenReturn(
            ByteArrayInputStream("random string is available".toByteArray())
        )

        val flow = sut.getInvestment()

        flow.test {
            val first = awaitItem()
            assert(first is Result.Loading)
            assert((first as Result.Loading).isLoading)

            val second = awaitItem()
            assert(second is Result.Loading)

            val third = awaitItem()
            assert(third is Result.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `parse json return error`(): Unit = runBlocking {

        val result = sut.parseJson("random string", Investment::class.java)

        assert(result is Result.Error)
    }

    @After
    fun tearDown() {

    }
}