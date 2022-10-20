package hector.ruiz.network

import hector.ruiz.network.api.ApiClient
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiClientTest {

    private val apiClient by lazy {
        ApiClient()
    }

    @Test
    fun `check interceptors on okHttpClient`() {
        with(apiClient) {
            assertEquals(this.okHttpClient.interceptors.size, 1)
        }
    }

    @Test
    fun `check timeout configuration on okHttpClient`() {
        with(apiClient) {
            assertEquals(this.okHttpClient.connectTimeoutMillis.toLong(), TIMEOUT)
            assertEquals(this.okHttpClient.readTimeoutMillis.toLong(), TIMEOUT)
        }
    }

    @Test
    fun `check okHttpClient configuration on retrofit`() {
        assertEquals(apiClient.retrofit.callFactory(), apiClient.okHttpClient)
    }

    @Test
    fun `check baseUrl configuration on retrofit`() {
        assertEquals(apiClient.retrofit.baseUrl().toString(), BASE_URL)
    }

    private companion object {
        const val TIMEOUT = 20L * 1000
        const val BASE_URL = "https://randomuser.me/"
    }
}