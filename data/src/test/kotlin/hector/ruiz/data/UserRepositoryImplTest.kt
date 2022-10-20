package hector.ruiz.data

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.data.repositories.UserRepositoryImpl
import hector.ruiz.domain.entities.UserResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class UserRepositoryImplTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var networkDataSource: NetworkDataSource

    private val userRepositoryImpl by lazy {
        UserRepositoryImpl(networkDataSource)
    }

    @Test
    fun `user repository requesting getUsers unsuccessfully`() {
        coEvery { networkDataSource.getUsers(PAGE_NUMBER) } returns ResponseResult(
            errorCode = ERROR_CODE,
            data = null
        )
        val result = runBlocking {
            userRepositoryImpl.getUsers(PAGE_NUMBER)
        }

        assertEquals(result.errorCode, ERROR_CODE)
        assertNull(result.data)
    }

    @Test
    fun `user repository requesting getUsers successfully`() {
        val userResponse = mockk<UserResponse>()

        coEvery { networkDataSource.getUsers(PAGE_NUMBER) } returns ResponseResult(
            errorCode = null,
            data = userResponse
        )
        val result = runBlocking {
            userRepositoryImpl.getUsers(PAGE_NUMBER)
        }

        assertNull(result.errorCode)
        assertEquals(result.data, userResponse)
    }

    private companion object {
        const val PAGE_NUMBER = 1
        const val ERROR_CODE = 400
    }
}