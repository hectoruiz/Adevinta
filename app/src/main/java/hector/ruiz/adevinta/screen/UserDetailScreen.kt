package hector.ruiz.adevinta.screen

import androidx.compose.runtime.Composable
import hector.ruiz.adevinta.ui.composables.UserInfo
import hector.ruiz.adevinta.ui.theme.AdevintaTheme
import hector.ruiz.domain.entities.User

@Composable
fun UserDetail(user: User?) {

    AdevintaTheme {
        UserInfo(user = user)
    }
}