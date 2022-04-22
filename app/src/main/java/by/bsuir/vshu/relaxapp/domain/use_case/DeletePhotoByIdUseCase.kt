package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class DeletePhotoByIdUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(id: Int) {
        return repository.deletePhotoById(id)
    }

}