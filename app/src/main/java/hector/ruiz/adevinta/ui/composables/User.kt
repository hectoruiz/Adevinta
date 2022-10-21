package hector.ruiz.adevinta.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import hector.ruiz.adevinta.R
import hector.ruiz.domain.entities.User
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun UserInfo(
    user: User?,
    modifier: Modifier? = Modifier
) {

    val painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
        .data(data = user?.picture?.large).apply(block = fun ImageRequest.Builder.() {
            placeholder(R.drawable.photo_placeholder)
            error(R.drawable.photo_error)
        }).build())
    val pictureProperties = modifier ?: Modifier
        .size(dimensionResource(id = R.dimen.picture_list_size))
        .clip(CircleShape)
        .border(dimensionResource(id = R.dimen.picture_border),
            MaterialTheme.colors.primary,
            CircleShape)

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.picture_description),
        contentScale = ContentScale.Crop,
        modifier = pictureProperties
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

@Composable
fun UserDetail(user: User?) {

    Text(
        style = MaterialTheme.typography.h2,
        text = "${user?.location?.street?.name} ${user?.location?.street?.number} - " +
                "${user?.location?.city} - ${user?.location?.state}",
        modifier = Modifier.padding(
            vertical = dimensionResource(id = R.dimen.small_padding_parent)
        ),
        textAlign = TextAlign.Center
    )
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val formatter = SimpleDateFormat("dd.MM.yyyy - HH:mm", Locale.getDefault())
    val registeredDate =
        user?.registered?.date?.let { date -> parser.parse(date)?.let { formatter.format(it) } }
    Text(
        style = MaterialTheme.typography.h3,
        text = "${stringResource(id = R.string.since_registered)}: $registeredDate"
    )

    var color = colorResource(id = R.color.blue_200)
    var painter = painterResource(id = R.drawable.ic_male)

    user?.gender.takeIf { it == FEMALE }?.let {
        color = colorResource(id = R.color.pink_200)
        painter = painterResource(id = R.drawable.ic_woman)
    }

    Icon(tint = color,
        painter = painter,
        contentDescription = stringResource(id = R.string.gender_description),
        modifier = Modifier
            .size(50.dp)
            .padding(top = dimensionResource(id = R.dimen.big_padding_parent)))

}

private const val FEMALE = "female"