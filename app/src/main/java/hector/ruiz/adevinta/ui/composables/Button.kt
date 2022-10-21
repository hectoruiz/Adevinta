package hector.ruiz.adevinta.ui.composables

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hector.ruiz.adevinta.R
import hector.ruiz.adevinta.ui.theme.AdevintaTheme

@Composable
fun FABButton(moreUsers: () -> Unit) {
    AdevintaTheme {
        FloatingActionButton(
            onClick = moreUsers,
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
            content = {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.more_users_description))
            }
        )
    }
}