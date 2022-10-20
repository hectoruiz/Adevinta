package hector.ruiz.adevinta.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import hector.ruiz.adevinta.R
import hector.ruiz.adevinta.ui.composables.CircularProgress
import hector.ruiz.adevinta.ui.composables.Item
import hector.ruiz.adevinta.ui.theme.AdevintaTheme
import hector.ruiz.adevinta.viewmodels.UserViewModel

@Composable
fun UserList(userViewModel: UserViewModel) {

    AdevintaTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(id = R.dimen.small_padding_parent))) {
            val userList = userViewModel.uiState.value.userList
            LazyColumn(
                content = {
                    items(userList.size) { index ->
                        Box(modifier =
                        Modifier.clickable {
                            println("Clicked on user: ${userList[index].name}")
                        }) {
                            Item(
                                userList[index],
                                painterResource(id = R.drawable.ic_delete),
                                stringResource(id = R.string.picture_description)) {
                                userViewModel.removeUser(userList[index])
                            }
                        }
                    }
                })
            if (userViewModel.uiState.value.loading) {
                CircularProgress(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}