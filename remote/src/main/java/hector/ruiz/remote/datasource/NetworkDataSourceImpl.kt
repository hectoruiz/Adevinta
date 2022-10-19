package hector.ruiz.remote.datasource

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.entities.UserResponse
import hector.ruiz.remote.api.ApiClient
import hector.ruiz.remote.api.ApiService
import retrofit2.create

class NetworkDataSourceImpl : NetworkDataSource {

    private val apiService: ApiService = ApiClient().retrofit.create()

    override fun getUsers(pageNumber: Int): ResponseResult<UserResponse> {
        return apiService.getCharacters(pageNumber, RESULTS, SEED).let {
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