package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class GetPhotoByIdUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(id: Int): Photo {
        return repository.getPhotoById(id)
    }

}