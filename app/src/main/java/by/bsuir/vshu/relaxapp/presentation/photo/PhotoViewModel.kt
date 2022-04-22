package by.bsuir.vshu.relaxapp.presentation.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
    private val deletePhotoByIdUseCase: DeletePhotoByIdUseCase,

) : ViewModel() {


    fun getPhoto(id: Int): Photo = runBlocking {
        withContext(Dispatchers.Default) { getPhotoByIdUseCase(id) }
    }

    fun deletePhoto(id: Int){
        viewModelScope.launch {
            deletePhotoByIdUseCase(id)
        }
    }


}