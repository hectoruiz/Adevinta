package hector.ruiz.adevinta.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import hector.ruiz.adevinta.R
import hector.ruiz.domain.entities.User

@Composable
fun UserInfo(user: User?) {

    val painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
        .data(data = user?.picture?.large).apply(block = fun ImageRequest.Builder.() {
            placeholder(R.drawable.photo_placeholder)
            error(R.drawable.photo_error)
        }).build())

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.picture_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.picture_size))
            .clip(CircleShape)
            .border(dimensionResource(id = R.dimen.picture_border),
                MaterialTheme.colors.primary,
                CircleShape)
    )
    Text(
        style = MaterialTheme.typography.h1,
        text = "${user?.name?.title} ${user?.name?.first} ${user?.name?.last}",
        modifier = Modifier.padding(
            vertical = dimensionResource(id = R.dimen.small_padding_parent)
        )
    )
    Text(
        style = MaterialTheme.typography.h2,
        text = "${user?.email}",
    )
    Text(
        style = MaterialTheme.typography.h2,
        text = "${user?.phone}"
    )
}