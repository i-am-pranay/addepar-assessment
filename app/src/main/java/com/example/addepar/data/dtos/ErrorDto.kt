package com.example.addepar.data.dtos


import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("error_description")
    val errorDescription: String? = null
)