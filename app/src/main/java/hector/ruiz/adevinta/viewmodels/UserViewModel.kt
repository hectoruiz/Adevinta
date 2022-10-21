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
    val filteredUsers: List<User> = listOf(),
    var searchText: String = ""
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

    fun getUsers() {
        viewModelScope.launch(exceptionHandler) {
            _uiListState.value = _uiListState.value.copy(loading = true)
            getUsersUseCase(_pageNumber.value).data?.let {
                val allUsers = _uiListState.value.userList + it
                _uiListState.value =
                    _uiListState.value.copy(filteredUsers = allUsers,
                        userList = allUsers,
                        loading = false)
                filterUsers(_uiListState.value.searchText)
                _pageNumber.value = _pageNumber.value.plus(1)
            }
        }
    }

    fun removeUser(user: User) {
        val auxUserList = _uiListState.value.userList.toMutableList()
        val auxUserFilter = _uiListState.value.filteredUsers.toMutableList()
        if (auxUserList.remove(user) && auxUserFilter.remove(user)) {
            _uiListState.value = _uiListState.value.copy(userList = auxUserList,
                removedUser = true,
                filteredUsers = auxUserFilter)
        } else _uiListState.value = _uiListState.value.copy(removedUser = false)
    }

    fun showedMessage() {
        _uiListState.value = _uiListState.value.copy(removedUser = false)
    }

    fun filterUsers(textField: String) {
        if (textField.isEmpty()) removeFilter()
        var filterList = if(textField.length < _uiListState.value.searchText.length){
            uiListState.value.userList
        } else _uiListState.value.filteredUsers

        _uiListState.value = _uiListState.value.copy(searchText = textField, loading = true)
        filterList = filterList.filter {
            with(it) {
                name?.first?.contains(textField, ignoreCase = true) == true ||
                        name?.last?.contains(textField, ignoreCase = true) == true ||
                        email?.contains(textField, ignoreCase = true) == true
            }
        }
        _uiListState.value = _uiListState.value.copy(filteredUsers = filterList, loading = false)
    }

    fun removeFilter() {
        _uiListState.value =
            _uiListState.value.copy(filteredUsers = _uiListState.value.userList, searchText = "")
    }
}