package hector.ruiz.adevinta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import hector.ruiz.adevinta.navigation.NavGraph
import hector.ruiz.adevinta.ui.theme.AdevintaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdevintaTheme {
                NavGraph()
            }
        }
    }
}