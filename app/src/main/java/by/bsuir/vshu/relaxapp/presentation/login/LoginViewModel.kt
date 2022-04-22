package by.bsuir.vshu.relaxapp.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.use_case.AddPhotoUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.AddUserUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val addPhotoUseCase: AddPhotoUseCase,
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

    fun addPhoto(photo: Photo) {
        viewModelScope.launch {
            addPhotoUseCase(photo)
        }
    }

}