package com.example.addepar.domain.useCase

import com.example.addepar.common.utils.Result
import com.example.addepar.domain.models.Investment
import com.example.addepar.domain.repository.InvestmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInvestmentUseCase @Inject constructor(private val investmentRepository: InvestmentRepository) {
    operator fun invoke(): Flow<Result<List<Investment>>> {
        return investmentRepository.getInvestment()
    }
}