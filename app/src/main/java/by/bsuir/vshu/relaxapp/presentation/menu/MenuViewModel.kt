package by.bsuir.vshu.relaxapp.presentation.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vshu.relaxapp.domain.model.Mood
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.use_case.GetMoodsUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.GetUserUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getMoodsUseCase: GetMoodsUseCase,
) : ViewModel() {

    var user: MutableLiveData<User> = MutableLiveData()
    var moods: MutableLiveData<List<Mood>> = MutableLiveData()


    fun loadUser(id: String) {
        viewModelScope.launch {
            user.value = getUserUseCase(id)!!
        }
    }

    fun updateUser(){
        viewModelScope.launch {
            updateUserUseCase(user.value!!)
        }
    }

    fun loadMoods(id: String){
        viewModelScope.launch {
            moods.value = getMoodsUseCase(id)!!
        }
    }

}