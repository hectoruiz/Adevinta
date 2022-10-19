package hector.ruiz.adevinta.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.data.repositories.UserRepositoryImpl
import hector.ruiz.domain.repositories.UserRepository
import hector.ruiz.remote.datasource.NetworkDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkDataSource(networkDataSourceImpl: NetworkDataSourceImpl): NetworkDataSource

    @Binds
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}