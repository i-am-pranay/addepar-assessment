package com.example.addepar.common.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

class TestDispatchers (
) : CustomDispatchers {
    override val IO: CoroutineDispatcher
        get() = TestCoroutineDispatcher()
    override val Main: CoroutineDispatcher
        get() = TestCoroutineDispatcher()
    override val Default: CoroutineDispatcher 
        get() = TestCoroutineDispatcher()
}