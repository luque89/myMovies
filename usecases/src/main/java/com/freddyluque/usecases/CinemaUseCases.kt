package com.freddyluque.usecases

import com.freddyluque.data.repository.CinemaRepository
import com.freddyluque.domain.Balance
import com.freddyluque.domain.Either
import com.freddyluque.domain.Movie

class CinemaUseCases(private val cinemaRepository: CinemaRepository) {
    suspend fun getMovies(cinema: String): Either<String, List<Movie>> = cinemaRepository.getMovies(cinema)
    suspend fun getTransactions(cardNumber: String, pin:String): Either<String,List<Balance>> = cinemaRepository.getTransactions(cardNumber,pin)
}