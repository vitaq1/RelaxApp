package by.bsuir.vshu.relaxapp.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.use_case.AddUserUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    var user: MutableLiveData<User> = MutableLiveData()


    init {

    }

    fun getUser(id: String): User = runBlocking {
        withContext(Dispatchers.Default) { getUserUseCase(id) }
    }

    fun addUser(user: User): Long = runBlocking {
        withContext(Dispatchers.Default) { addUserUseCase(user) }
    }

}