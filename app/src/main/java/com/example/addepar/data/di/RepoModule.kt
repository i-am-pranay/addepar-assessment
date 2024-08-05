package com.example.addepar.data.di

import android.content.Context
import com.example.addepar.data.repository.InvestmentRepoImpl
import com.example.addepar.domain.repository.InvestmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideInvestmentRepository(@ApplicationContext context: Context): InvestmentRepository {
        return InvestmentRepoImpl(
            context
        )
    }
}