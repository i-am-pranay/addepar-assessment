package com.example.addepar.common.utils

import com.example.addepar.common.constants.Constants

sealed class Screens(val route: String) {
    object Investment: Screens(Constants.ScreenName.INVESTMENT_SCREEN)
    object InvestmentDetails: Screens(Constants.ScreenName.INVESTMENT_DETAILS_SCREEN)
}