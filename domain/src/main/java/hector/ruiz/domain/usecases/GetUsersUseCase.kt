package hector.ruiz.domain.usecases

import hector.ruiz.commons.ResponseResult
import hector.ruiz.domain.entities.User
import hector.ruiz.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val defaultDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(pageNumber: Int): ResponseResult<List<User>> {
        val result = userRepository.getUsers(pageNumber)
        return withContext(defaultDispatcher) {
            ResponseResult(data = result.data?.results?.filterNotNull(),
                errorCode = result.errorCode)
        }
    }
}