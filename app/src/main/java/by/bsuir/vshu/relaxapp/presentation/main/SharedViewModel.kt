package by.bsuir.vshu.relaxapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.use_case.*
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
    private val getPhotosUseCase: GetPhotosUseCase,
    private val addPhotoUseCase: AddPhotoUseCase,
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
    private val addMoodUseCase: AddMoodUseCase,
) : ViewModel() {

    var horoscope: MutableLiveData<Horoscope> = MutableLiveData()
    var user: MutableLiveData<User> = MutableLiveData()
    var photos: MutableLiveData<List<Photo>> = MutableLiveData()
    var mood: MutableLiveData<Mood> = MutableLiveData()

    init {
        loadHoroscope()
    }

    private fun loadHoroscope() {
        viewModelScope.launch {
            horoscope.value = getHoroscopeUseCase()!!
        }
    }

    fun loadPhotos(id: String) {
        viewModelScope.launch {
            photos.value = getPhotosUseCase(id)!!
        }
    }

    fun getPhoto(id: Int): Photo = runBlocking {
        withContext(Dispatchers.Default) { getPhotoByIdUseCase(id) }
    }

    fun getPhotos(id: String): List<Photo> = runBlocking {
        withContext(Dispatchers.Default) { getPhotosUseCase(id) }
    }

    fun addPhoto(photo: Photo) {
        viewModelScope.launch {
            addPhotoUseCase(photo)
        }
    }
    fun addMood(mood: by.bsuir.vshu.relaxapp.domain.model.Mood) {
        viewModelScope.launch {
            addMoodUseCase(mood)
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

fun <T> MutableLiveData<T>.forceRefresh() {
    this.value = this.value
}

