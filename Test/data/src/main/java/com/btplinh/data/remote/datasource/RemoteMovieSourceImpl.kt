package com.btplinh.data.remote.datasource

import com.btplinh.domain.basenetwork.NetworkStatus
import com.btplinh.data.remote.ApiService
import com.btplinh.data.dto.OmdFilmResponse
import com.btplinh.domain.basenetwork.ErrorBody
import com.btplinh.domain.basenetwork.MessageError
import com.btplinh.domain.entities.Movie
import com.btplinh.domain.repository.MovieRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RemoteMovieSourceImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getListMovie(film: String, page: Int): Flow<NetworkStatus<List<Movie>>> {
        return flow{
            emit(NetworkStatus.Loading())
            val response = apiService.getListMovie(film = film, page = page)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                val odmFilm = OmdFilmResponse(body.Search, body.totalResults, body.Response)
                emit(NetworkStatus.Success(odmFilm.Search))
            } else {
                val messageError = processErrorBody(response)
                emit(NetworkStatus.Error(messageError))
            }
        }
    }

    private fun processErrorBody(response: Response<OmdFilmResponse>): String? {
        try {
            val messageError = response.errorBody()?.let {
                val errorJson = it.string()
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(ErrorBody::class.java)
                jsonAdapter.fromJson(errorJson)?.Error ?: response.message()
            } ?: response.message()
            return messageError
        } catch (e: Exception) {
            return when (e) {
                is ConnectException -> {
                    MessageError.CONNECT_EXCEPTION.name
                }
                is UnknownHostException -> {
                    MessageError.UNKNOWN_HOST_EXCEPTION.name
                }
                is SocketTimeoutException -> {
                    MessageError.SOCKET_TIME_OUT_EXCEPTION.name
                }
                is HttpException -> {
                    MessageError.UNKNOWN_NETWORK_EXCEPTION.name
                }
                else -> {
                    MessageError.UNKNOWN_NETWORK_EXCEPTION.name
                }
            }
        }
    }
}
