package com.btplinh.domain.repository

import com.btplinh.domain.basenetwork.NetworkStatus
import com.btplinh.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getListMovie(film: String, page: Int): Flow<NetworkStatus<List<Movie>>>
}