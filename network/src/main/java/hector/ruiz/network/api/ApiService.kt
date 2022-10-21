package hector.ruiz.network.api

import hector.ruiz.domain.entities.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/?exc=dob,login,id")
    suspend fun getUsers(
        @Query(PAGE_NUMBER) pageNumber: Int,
        @Query(RESULTS) results: Int,
        @Query(SEED) seed: String
    ): Response<UserResponse>

    private companion object {
        const val PAGE_NUMBER = "page"
        const val RESULTS = "results"
        const val SEED = "seed"
    }
}