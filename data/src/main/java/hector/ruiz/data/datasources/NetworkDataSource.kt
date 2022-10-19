package hector.ruiz.data.datasources

import hector.ruiz.domain.entities.User

interface NetworkDataSource {

    fun getUsers(amount: Int): List<User>
}