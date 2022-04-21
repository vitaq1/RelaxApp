package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import by.bsuir.vshu.relaxapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(user: User): Long {
        return repository.addUser(user)
    }

}