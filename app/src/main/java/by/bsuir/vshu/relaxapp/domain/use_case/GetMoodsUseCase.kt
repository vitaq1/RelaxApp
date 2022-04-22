package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Mood
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class GetMoodsUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(id: String): List<Mood> {
        return repository.getMoodsByUserId(id)
    }

}