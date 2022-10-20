package hector.ruiz.network.datasource

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.entities.UserResponse
import hector.ruiz.network.api.ApiService
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    NetworkDataSource {

    override suspend fun getUsers(pageNumber: Int): ResponseResult<UserResponse> {
        return apiService.getUsers(pageNumber, RESULTS, SEED).let {
            if (it.isSuccessful) {
                ResponseResult(data = it.body(), errorCode = null)
            } else {
                ResponseResult(data = null, errorCode = it.code())
            }
        }
    }

    private companion object {
        const val RESULTS = 10
        const val SEED = "abc"
    }
}