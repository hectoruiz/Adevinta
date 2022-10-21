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
    val userList: List<User> = listOf(),
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

    private val _uiListState = mutableStateOf(ListUiState())
    val uiListState: State<ListUiState> = _uiListState

    private val _pageNumber = mutableStateOf(1)

    init {
        getUsers()
    }

    fun removeUser(user: User) {
        val auxList = _uiListState.value.userList.toMutableList()
        if (auxList.remove(user)) {
            _uiListState.value = _uiListState.value.copy(userList = auxList, removedUser = true)
        } else _uiListState.value = _uiListState.value.copy(removedUser = false)
    }

    fun getUsers() {
        viewModelScope.launch(exceptionHandler) {
            _uiListState.value = _uiListState.value.copy(loading = true)
            getUsersUseCase(_pageNumber.value).data?.let {
                _uiListState.value =
                    _uiListState.value.copy(userList = _uiListState.value.userList + it,
                        loading = false)
                _pageNumber.value = _pageNumber.value.plus(1)
            }
        }
    }

    fun showedMessage() {
        _uiListState.value = _uiListState.value.copy(removedUser = false)
    }
}