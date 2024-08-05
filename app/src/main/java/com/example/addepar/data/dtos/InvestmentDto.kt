package com.example.addepar.data.dtos


import com.google.gson.annotations.SerializedName

data class InvestmentDto(
    @SerializedName("details")
    val details: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("principal")
    val principal: String? = null,
    @SerializedName("ticker")
    val ticker: String? = null,
    @SerializedName("value")
    val value: String? = null
)