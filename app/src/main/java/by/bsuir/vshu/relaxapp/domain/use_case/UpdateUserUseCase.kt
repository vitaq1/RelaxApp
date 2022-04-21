package by.bsuir.vshu.relaxapp.domain.use_case

import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: HoroscopeRepository
) {

    suspend operator fun invoke(user: User) {
        return repository.updateUser(user)
    }

}