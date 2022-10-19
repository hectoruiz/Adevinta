package hector.ruiz.data.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.UserResponse
import hector.ruiz.domain.repositories.UserRepository

class UserRepositoryImpl : UserRepository {

    override fun getUsers(pageNumber: Int): ResponseResult<UserResponse> {
        TODO("Not yet implemented")
    }
}