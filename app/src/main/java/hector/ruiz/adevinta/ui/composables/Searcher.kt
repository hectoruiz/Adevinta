package hector.ruiz.adevinta.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import hector.ruiz.adevinta.R

@Composable
fun Searcher(
    text: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onClearButton: () -> Unit,
) {
    OutlinedTextField(
        value = text,
        placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.big_padding_parent),
                dimensionResource(id = R.dimen.small_padding_parent),
                dimensionResource(id = R.dimen.big_padding_parent),
                dimensionResource(id = R.dimen.small_padding_parent)),
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Clear,
                contentDescription = stringResource(id = R.string.clear_search_description),
                modifier = Modifier.clickable {
                    onClearButton()
                })
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
            onTextChanged(it)
        })
}