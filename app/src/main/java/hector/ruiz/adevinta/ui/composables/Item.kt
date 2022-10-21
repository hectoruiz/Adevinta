package hector.ruiz.adevinta.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import hector.ruiz.adevinta.R
import hector.ruiz.adevinta.ui.theme.AdevintaTheme
import hector.ruiz.domain.entities.User

@Composable
fun Item(
    user: User?,
    icon: Painter?,
    contentDescription: String?,
    onRemoveClick: ((User?) -> Unit)?
) {

    AdevintaTheme {
        Card(elevation = dimensionResource(id = R.dimen.picture_elevation),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.big_padding_parent),
                    dimensionResource(id = R.dimen.small_padding_parent),
                    dimensionResource(id = R.dimen.big_padding_parent),
                    dimensionResource(id = R.dimen.small_padding_parent))
                .fillMaxSize()) {
            Box {
                Column(modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.small_padding_parent))
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    UserInfo(user = user, null)
                }
                icon?.let {
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopEnd),
                        onClick = {
                            onRemoveClick?.invoke(user)
                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = contentDescription)
                    }
                }
            }
        }
    }
}