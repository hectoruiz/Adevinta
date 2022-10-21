package hector.ruiz.adevinta.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import hector.ruiz.adevinta.R
import hector.ruiz.adevinta.ui.composables.UserDetail
import hector.ruiz.adevinta.ui.composables.UserInfo
import hector.ruiz.adevinta.ui.theme.AdevintaTheme
import hector.ruiz.domain.entities.User

@Composable
fun UserDetailScreen(user: User?) {

    AdevintaTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(id = R.string.app_title)) })
            },
            content = { paddingValues ->
                Box(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(id = R.dimen.small_padding_parent))) {

                    Column(modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.small_padding_parent))
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        UserInfo(user = user)
                        UserDetail(user = user)
                    }
                    val color = if (user?.gender == FEMALE) {
                        Color.Red
                    } else Color.Blue
                    Icon(tint = color,
                        painter = painterResource(id = R.drawable.ic_gender),
                        contentDescription = stringResource(id = R.string.gender_description),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(dimensionResource(id = R.dimen.gender_size)))
                }
            }
        )
    }
}

private const val FEMALE = "female"