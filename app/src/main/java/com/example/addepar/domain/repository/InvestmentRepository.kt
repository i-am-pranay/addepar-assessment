package com.example.addepar.domain.repository

import com.example.addepar.common.utils.Result
import com.example.addepar.domain.models.Investment
import kotlinx.coroutines.flow.Flow

interface InvestmentRepository {
    fun getInvestment(): Flow<Result<List<Investment>>>
    suspend fun readFromAssets(fileId: Int): Result<String>
    suspend fun<T> parseJson(jsonString: String, type: Class<T>): Result<T>
}