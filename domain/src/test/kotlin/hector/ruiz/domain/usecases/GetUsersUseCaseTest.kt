package hector.ruiz.domain.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.User
import hector.ruiz.domain.entities.UserResponse
import hector.ruiz.domain.repositories.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GetUsersUseCaseTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var userRepository: UserRepository

    @MockK
    private lateinit var dispatcher: Dispatchers

    private val getUsersUseCase by lazy {
        GetUsersUseCase(userRepository, dispatcher.Default)
    }

    @Test
    fun `getUsersUseCase request successfully getUsers`() {
        val responseData = mockk<ResponseResult<UserResponse>>()
        val userResponse = mockk<UserResponse>()
        val results = listOf(mockk<User>(), mockk(), null)
        every { responseData.data } returns userResponse
        every { userResponse.results } returns results
        every { responseData.errorCode } returns null

        coEvery { userRepository.getUsers(PAGE_NUMBER) } returns responseData

        val result = runBlocking { getUsersUseCase(PAGE_NUMBER) }

        assertNull(result.errorCode)
        assertEquals(result.data, results.filterNotNull())
        assertEquals(2, result.data?.size)
    }

    @Test
    fun `getUsersUseCase request successfully getUsers with null user list`() {
        val responseData = mockk<ResponseResult<UserResponse>>()
        val userResponse = mockk<UserResponse>()
        val results = listOf(null, null)
        every { responseData.data } returns userResponse
        every { userResponse.results } returns results
        every { responseData.errorCode } returns null

        coEvery { userRepository.getUsers(PAGE_NUMBER) } returns responseData

        val result = runBlocking { getUsersUseCase(PAGE_NUMBER) }

        assertNull(result.errorCode)
        assertEquals(result.data, results.filterNotNull())
        assertEquals(0, result.data?.size)
    }

    @Test
    fun `getUsersUseCase request unsuccessfully getUsers`() {
        val responseData = mockk<ResponseResult<UserResponse>>()
        every { responseData.data } returns null
        every { responseData.errorCode } returns ERROR_CODE

        coEvery { userRepository.getUsers(PAGE_NUMBER) } returns responseData

        val result = runBlocking { getUsersUseCase(PAGE_NUMBER) }

        assertEquals(ERROR_CODE, result.errorCode)
        assertNull(result.data)
    }

    private companion object {
        const val PAGE_NUMBER = 2
        const val ERROR_CODE = 400
    }
}