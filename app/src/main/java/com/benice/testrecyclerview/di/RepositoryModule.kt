package com.benice.testrecyclerview.di

import com.benice.testrecyclerview.data.ElementRepositoryImpl
import com.benice.testrecyclerview.domain.ElementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: ElementRepositoryImpl): ElementRepository

}
