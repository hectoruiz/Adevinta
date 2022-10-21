package hector.ruiz.adevinta.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import hector.ruiz.adevinta.R
import hector.ruiz.adevinta.ui.theme.AdevintaTheme

@Composable
fun CircularProgress(modifier: Modifier) {

    AdevintaTheme {
        CircularProgressIndicator(
            modifier = modifier
                .size(dimensionResource(id = R.dimen.progress_size)))
    }
}