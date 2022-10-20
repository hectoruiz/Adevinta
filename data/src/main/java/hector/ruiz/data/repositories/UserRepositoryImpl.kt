package hector.ruiz.data.repositories

import hector.ruiz.commons.ResponseResult
import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.entities.UserResponse
import hector.ruiz.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val networkDataSource: NetworkDataSource) :
    UserRepository {

    override suspend fun getUsers(pageNumber: Int): ResponseResult<UserResponse> {
        return networkDataSource.getUsers(pageNumber)
    }
}