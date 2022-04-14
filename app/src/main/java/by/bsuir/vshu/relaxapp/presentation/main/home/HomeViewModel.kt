package by.bsuir.vshu.relaxapp.presentation.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.use_case.GetHoroscopeUseCase
import by.bsuir.vshu.relaxapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHoroscopeUseCase: GetHoroscopeUseCase
) : ViewModel() {

    var horoscope: MutableLiveData<Horoscope> = MutableLiveData()

    init {
        getHoroscope()
    }

    private fun getHoroscope(){
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

}