package com.btplinh.data.remote

import com.btplinh.data.BuildConfig
import com.btplinh.data.dto.OmdFilmResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //https://www.omdbapi.com/?apikey=b9bd48a6&s=Marvel&type=movie
    @GET("?")
    suspend fun getListMovie(
        @Query("s") film: String,
        @Query("page") page: Int = 1,
        @Query("type") type: String = "movie",
        @Query("apikey") appid: String = BuildConfig.API_KEY
    ): Response<OmdFilmResponse>
}