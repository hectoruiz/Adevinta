package hector.ruiz.adevinta

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun displaySnackBarShort(
    scope: CoroutineScope,
    message: String,
    snackbarHostState: SnackbarHostState
) {
    displaySnackBar(scope, message, snackbarHostState, SnackbarDuration.Short)
}

fun displaySnackBarLong(
    scope: CoroutineScope,
    message: String,
    snackbarHostState: SnackbarHostState
) {
    displaySnackBar(scope, message, snackbarHostState, SnackbarDuration.Long)
}

fun displaySnackBarIndefinite(
    scope: CoroutineScope,
    message: String,
    snackbarHostState: SnackbarHostState
) {
    displaySnackBar(scope, message, snackbarHostState, SnackbarDuration.Indefinite)
}

private fun displaySnackBar(
    scope: CoroutineScope,
    message: String,
    snackbarHostState: SnackbarHostState,
    duration: SnackbarDuration
) {
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        snackbarHostState.showSnackbar(
            message = message,
            duration = duration,
        )
    }
}
