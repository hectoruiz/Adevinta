package hector.ruiz.adevinta.screen

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import hector.ruiz.adevinta.R
import hector.ruiz.adevinta.displaySnackBarShort
import hector.ruiz.adevinta.ui.composables.CircularProgress
import hector.ruiz.adevinta.ui.composables.Item
import hector.ruiz.adevinta.ui.theme.AdevintaTheme
import hector.ruiz.adevinta.viewmodels.UserViewModel
import hector.ruiz.domain.entities.User

@Composable
fun UserListScreen(navController: NavHostController) {

    val userViewModel = hiltViewModel<UserViewModel>()

    AdevintaTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(id = R.string.app_title)) })
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            content = { paddingValues ->
                Box(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(id = R.dimen.small_padding_parent))) {
                    val userList = userViewModel.uiListState.value.userList
                    LazyColumn(
                        content = {
                            items(userList.size) { index ->
                                Box(modifier =
                                Modifier.clickable {
                                    val moshi = Moshi.Builder().build()
                                    val jsonAdapter: JsonAdapter<User> =
                                        moshi.adapter(User::class.java)
                                    val userJSON = Uri.encode(jsonAdapter.toJson(userList[index]))
                                    navController.navigate("detaillist/$userJSON")
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
                    if (userViewModel.uiListState.value.loading) {
                        CircularProgress(modifier = Modifier.align(Alignment.Center))
                    }
                    if (userViewModel.uiListState.value.removedUser) {
                        displaySnackBarShort(
                            scope = scope, snackbarHostState = snackbarHostState,
                            message = stringResource(id = R.string.remove_success))
                        userViewModel.showedMessage()
                    }
                }
            }
        )
    }
}