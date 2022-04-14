package by.bsuir.vshu.relaxapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.use_case.GetHoroscopeUseCase
import by.bsuir.vshu.relaxapp.domain.use_case.GetRecommendationUseCase
import by.bsuir.vshu.relaxapp.util.Mood
import by.bsuir.vshu.relaxapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase,
    private val getRecommendationUseCase: GetRecommendationUseCase
) : ViewModel() {

    var horoscope: MutableLiveData<Horoscope> = MutableLiveData()

    init {
        loadHoroscope()
    }

    private fun loadHoroscope() {
        getHoroscopeUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    horoscope.value = result.data!!
                }
                is Resource.Error -> {
                }
                is Resource.Loading -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getHoroscope(): String {
        return horoscope.value!!.date + "\n" + horoscope.value!!.description
    }

    fun getRecommendationByMood(mood: Mood): String {
        return getRecommendationUseCase(mood)
    }

}