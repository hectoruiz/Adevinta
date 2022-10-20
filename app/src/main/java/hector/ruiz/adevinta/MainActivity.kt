package hector.ruiz.adevinta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.adevinta.screen.UserList
import hector.ruiz.adevinta.ui.theme.AdevintaTheme
import hector.ruiz.adevinta.viewmodels.UserViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdevintaTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = stringResource(id = R.string.app_title)) })
                    },
                    content = { paddingValues ->
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)) {
                            UserList(userViewModel)
                        }
                    }
                )
            }
        }
    }
}