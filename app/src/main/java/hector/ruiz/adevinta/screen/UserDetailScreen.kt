package hector.ruiz.adevinta.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
                TopAppBar(title = { Text(text = stringResource(id = R.string.detail_title)) })
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
                        UserInfo(
                            user = user,
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.picture_detail_size)))
                        UserDetail(user = user)
                    }
                }
            }
        )
    }
}