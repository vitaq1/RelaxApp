package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import by.bsuir.vshu.relaxapp.util.Mood
import by.bsuir.vshu.relaxapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    operator fun invoke(mood: Mood): String {
        return repository.getRecommendation(mood)
    }

}