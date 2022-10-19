package hector.ruiz.domain.repositories

import hector.ruiz.domain.entities.User

interface UserRepository {

    fun getUsers(amount: Int): List<User>
}