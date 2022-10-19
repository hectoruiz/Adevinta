package hector.ruiz.remote.datasource

import hector.ruiz.data.datasources.NetworkDataSource
import hector.ruiz.domain.entities.User

class NetworkDataSourceImpl : NetworkDataSource {

    override fun getUsers(amount: Int): List<User> {
        TODO("Not yet implemented")
    }
}