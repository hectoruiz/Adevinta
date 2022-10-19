package hector.ruiz.adevinta.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hector.ruiz.remote.api.ApiClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providerApiClient(apiClient: ApiClient): Retrofit {
        return apiClient.retrofit
    }
}