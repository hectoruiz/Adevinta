package hector.ruiz.adevinta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import hector.ruiz.adevinta.screen.UserDetailScreen
import hector.ruiz.adevinta.screen.UserListScreen
import hector.ruiz.domain.entities.User

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = START_DESTINATION) {
        composable(START_DESTINATION) {
            UserListScreen(navController)
        }
        composable(route = "$FINAL_DESTINATION/{$ARGUMENT}") {
            val json = it.arguments?.getString(ARGUMENT)
            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<User> = moshi.adapter(User::class.java)
            json?.let { it1 -> jsonAdapter.fromJson(it1) }?.let { user ->
                UserDetailScreen(user)
            }
        }
    }
}

private const val START_DESTINATION = "userlist"
private const val FINAL_DESTINATION = "detaillist"
private const val ARGUMENT = "user"