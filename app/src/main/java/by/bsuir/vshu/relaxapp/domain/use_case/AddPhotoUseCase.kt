package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class AddPhotoUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(photo: Photo) {
        return repository.addPhoto(photo)
    }

}