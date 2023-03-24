package com.btplinh.domain.usecase

import com.btplinh.domain.basenetwork.NetworkStatus
import com.btplinh.domain.entities.Movie
import com.btplinh.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend fun execute(film: String, page: Int): Flow<NetworkStatus<List<Movie>>> {
        return movieRepository.getListMovie(film = film, page = page)
    }
}
