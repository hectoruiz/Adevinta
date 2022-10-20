package hector.ruiz.adevinta.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hector.ruiz.domain.entities.User
import hector.ruiz.domain.usecases.GetUsersUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ListUiState(
    val userList: List<User> = emptyList(),
    val loading: Boolean = true,
    val removedUser: Boolean = false,
)

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _uiState = mutableStateOf(ListUiState())
    val uiState: State<ListUiState> = _uiState

    private val _pageNumber = mutableStateOf(1)

    init {
        getUser()
    }

    fun removeUser(user: User) {
        val auxList = _uiState.value.userList.toMutableList()
        if (auxList.remove(user)) {
            _uiState.value = _uiState.value.copy(userList = auxList, removedUser = true)
        } else _uiState.value = _uiState.value.copy(removedUser = false)
    }

    private fun getUser() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = _uiState.value.copy(loading = true)
            getUsersUseCase(_pageNumber.value).data?.let {
                _uiState.value = _uiState.value.copy(userList = it, loading = false)
                _pageNumber.value = _pageNumber.value.plus(1)
            }
        }
    }
}