package hector.ruiz.network.datasource

import hector.ruiz.domain.entities.UserResponse
import hector.ruiz.network.api.ApiClient
import hector.ruiz.network.api.ApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class NetworkDataSourceImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var apiClient: ApiClient

    @MockK
    private lateinit var retrofit: Retrofit

    @MockK
    private lateinit var apiService: ApiService

    private val networkDataSourceImpl by lazy {
        NetworkDataSourceImpl(apiService)
    }

    @Before
    fun setUp() {
        every { apiClient.retrofit } returns retrofit
        every { retrofit.create<ApiService>() } returns apiService
    }

    @Test
    fun `error requesting users`() {
        coEvery { apiService.getUsers(PAGE_NUMBER, RESULTS, SEED) } returns Response.error(
            ERROR_CODE,
            mockk(relaxed = true)
        )
        val result = runBlocking {
            networkDataSourceImpl.getUsers(PAGE_NUMBER)
        }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    @Test
    fun `success requesting users`() {
        val responseData = mockk<UserResponse>()
        coEvery { apiService.getUsers(PAGE_NUMBER, RESULTS, SEED) } returns Response.success(
            SUCCESS_CODE,
            responseData
        )
        val result = runBlocking {
            networkDataSourceImpl.getUsers(PAGE_NUMBER)
        }

        assertNull(result.errorCode)
        assertEquals(responseData, result.data)
    }

    private companion object {
        const val RESULTS = 10
        const val SEED = "abc"
        const val PAGE_NUMBER = 1
        const val ERROR_CODE = 400
        const val SUCCESS_CODE = 200
    }
}