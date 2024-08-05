package com.example.addepar.data.dtos


import com.google.gson.annotations.SerializedName

data class InvestmentResponseDto(
    @SerializedName("investments")
    val investments: List<InvestmentDto>? = listOf()
)