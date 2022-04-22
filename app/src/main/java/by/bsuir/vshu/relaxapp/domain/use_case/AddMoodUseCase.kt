package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Mood
import by.bsuir.vshu.relaxapp.domain.model.Photo
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class AddMoodUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(mood: Mood) {
        return repository.addMood(mood)
    }

}