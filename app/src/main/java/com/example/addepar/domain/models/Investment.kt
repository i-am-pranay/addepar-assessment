package com.example.addepar.domain.models

import com.example.addepar.R
import com.example.addepar.common.utils.ValueColor

data class Investment(
    val details: String = "",
    val name: String = "",
    val principal: String = "",
    val ticker: String = "",
    val value: String = "",
) {

    val valColor: ValueColor
        get() =
            try {
                if (value.toLong() > principal.toLong()) {
                    ValueColor.Profit()
                } else if (value.toLong() < principal.toLong()) {
                    ValueColor.Loss()
                } else {
                    ValueColor.Unknown()
                }
            } catch (e: Exception) {
                ValueColor.Unknown()
            }

    val icon: Int?
        get() = try {
            if (value.toLong() > principal.toLong()) {
                R.drawable.growth_icon
            } else if (value.toLong() < principal.toLong()) {
                R.drawable.loss_icon
            } else {
                null
            }
        } catch (e: Exception) {
           null
        }

    companion object {
        val preview =
            Investment(
                name = "Netflx",
                ticker = "NFLX",
                value = "8900000000",
                principal = "5000000000",
                details = "A video production company, streaming giant, and maker of movies."
            )

    }
}