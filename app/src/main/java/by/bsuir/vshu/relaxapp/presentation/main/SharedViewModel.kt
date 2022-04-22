package by.bsuir.vshu.relaxapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.use_case.GetHoroscopeUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.GetRecommendationUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.GetUserUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.UpdateUserUseCase
import by.bsuir.vshu.relaxapp.util.Mood
import by.bsuir.vshu.relaxapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase,
    private val getRecommendationUseCase: GetRecommendationUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel() {

    var horoscope: MutableLiveData<Horoscope> = MutableLiveData()
    var user: MutableLiveData<User> = MutableLiveData()

    init {
        loadHoroscope()
    }

    private fun loadHoroscope() {
        viewModelScope.launch {
            horoscope.value = getHoroscopeUseCase()!!
        }
    }

    fun getHoroscope(): String {
        return horoscope.value!!.date + "\n" + horoscope.value!!.description
    }

    fun getRecommendationByMood(mood: Mood): String {
        return getRecommendationUseCase(mood)
    }

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
}

