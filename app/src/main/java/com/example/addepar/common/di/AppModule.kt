package com.example.addepar.common.di

import com.example.addepar.common.utils.CustomDispatchers
import com.example.addepar.common.utils.ViewModelDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDispatchers(): CustomDispatchers {
        return ViewModelDispatchers()
    }
}