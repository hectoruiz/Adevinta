package hector.ruiz.adevinta.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import hector.ruiz.adevinta.R
import hector.ruiz.adevinta.displaySnackBarShort
import hector.ruiz.adevinta.ui.composables.CircularProgress
import hector.ruiz.adevinta.ui.composables.FABButton
import hector.ruiz.adevinta.ui.composables.Searcher
import hector.ruiz.adevinta.ui.composables.UserList
import hector.ruiz.adevinta.ui.theme.AdevintaTheme
import hector.ruiz.adevinta.viewmodels.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun UserListScreen(navController: NavHostController) {

    val userViewModel = hiltViewModel<UserViewModel>()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(key1 = text) {
        delay(1500L)
        userViewModel.filterUsers(text.text)
    }

    AdevintaTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(id = R.string.list_title)) })
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            content = { paddingValues ->
                Box(modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(vertical = dimensionResource(id = R.dimen.small_padding_parent))) {
                    val userList = userViewModel.uiListState.value.filteredUsers
                    Column {
                        Searcher(text,
                            { newText ->
                                text = newText
                            },
                            {
                                userViewModel.removeFilter()
                                text = TextFieldValue("")
                            })
                        UserList(userList, { param ->
                            navController.navigate("detaillist/$param")
                        }, { user ->
                            userViewModel.removeUser(user)
                        })
                    }

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
            },
            floatingActionButton = {
                FABButton { userViewModel.getUsers() }
            }
        )
    }
}