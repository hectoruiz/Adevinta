package hector.ruiz.data.repositories

import hector.ruiz.domain.entities.User
import hector.ruiz.domain.repositories.UserRepository

class UserRepositoryImpl : UserRepository {

    override fun getUsers(amount: Int): List<User> {
        TODO("Not yet implemented")
    }
}