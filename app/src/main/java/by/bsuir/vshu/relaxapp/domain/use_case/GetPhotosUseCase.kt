package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(id: String): List<Photo> {
        return repository.getPhotosByUserId(id)
    }

}