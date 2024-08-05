package com.example.addepar.common.utils

import kotlinx.coroutines.CoroutineDispatcher

interface CustomDispatchers {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Default: CoroutineDispatcher
}