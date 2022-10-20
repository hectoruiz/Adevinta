package hector.ruiz.domain.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.UserResponse

interface UserRepository {

    suspend fun getUsers(pageNumber: Int): ResponseResult<UserResponse>
}