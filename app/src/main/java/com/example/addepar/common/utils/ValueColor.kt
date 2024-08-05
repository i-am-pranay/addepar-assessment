package com.example.addepar.common.utils

import androidx.compose.ui.graphics.Color

sealed class ValueColor(val color: Color) {
    class Profit(): ValueColor(InvestmentColorTheme.profit_color)
    class Loss(): ValueColor(InvestmentColorTheme.loss_color)
    class Unknown(): ValueColor(InvestmentColorTheme.unknown_color)
}