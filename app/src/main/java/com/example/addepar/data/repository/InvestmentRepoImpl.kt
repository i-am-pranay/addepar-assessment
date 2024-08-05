package com.example.addepar.data.repository

import android.content.Context
import com.example.addepar.R
import com.example.addepar.domain.models.Investment
import com.example.addepar.domain.repository.InvestmentRepository
import kotlinx.coroutines.flow.Flow
import com.example.addepar.common.utils.Result
import com.example.addepar.domain.models.InvestmentResponse
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class InvestmentRepoImpl @Inject constructor(
    @ApplicationContext private val context: Context
): InvestmentRepository {
    override fun getInvestment(): Flow<Result<List<Investment>>> {
        return kotlinx.coroutines.flow.flow {
            emit(Result.Loading(isLoading = true))

            val result = readFromAssets(R.raw.investments)

            if (result is Result.Error) {
                emit(Result.Error(message = result.message))
                return@flow
            }

            if (result is Result.Success) {
                val investments = parseJson(result.data ?: "", InvestmentResponse::class.java)

                if (investments is Result.Error) {
                    emit(Result.Loading(isLoading = false))
                    emit(Result.Error(message = investments.message))
                    return@flow
                }

                if (investments is Result.Success) {
                    emit(Result.Loading(isLoading = false))
                    emit(Result.Success(data = investments.data?.investments ?: emptyList()))
                }
            }

            emit(Result.Loading(isLoading = false))
            return@flow
        }
    }

    override suspend fun readFromAssets(fileId: Int): Result<String> {
      try {
          val file = context.resources.openRawResource(fileId)
          val bufferReader = BufferedReader(InputStreamReader(file))

          val stringBuilder = StringBuilder()
          bufferReader.useLines { lines ->
              lines.forEach {
                  stringBuilder.append(it)
              }
          }

          val jsonString = stringBuilder.toString()
          return Result.Success(data = jsonString)
      } catch (e: Exception) {
          return Result.Error(message = "Error in reading file: $e")
      }
    }

    override suspend fun<T> parseJson(jsonString: String, type: Class<T>): Result<T> {
        try {
            val parsedObject = Gson().fromJson<T>(jsonString, type)
            return Result.Success(data = parsedObject)
        } catch (e: Exception) {
            return Result.Error(message = "Error in parsing: $e")
        }
    }


}