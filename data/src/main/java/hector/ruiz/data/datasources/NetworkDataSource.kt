package hector.ruiz.data.datasources

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.UserResponse

interface NetworkDataSource {

    fun getUsers(pageNumber: Int): ResponseResult<UserResponse>
}