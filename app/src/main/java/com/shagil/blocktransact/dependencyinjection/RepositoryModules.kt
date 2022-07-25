package com.shagil.blocktransact.dependencyinjection

import com.shagil.blocktransact.data.repository.BlockTransactRepositoryImpl
import com.shagil.blocktransact.domain.repository.BlockTransactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // for abstract functions we don't use @Provides we rather use @Binds

    @Binds
    @Singleton
    abstract fun bindBlockTransactRepository(
        blockTransactRepositoryImpl: BlockTransactRepositoryImpl
    ):BlockTransactRepository
}