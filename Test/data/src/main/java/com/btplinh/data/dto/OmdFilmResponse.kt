package com.btplinh.data.dto

import com.btplinh.domain.entities.Movie

data class OmdFilmResponse(
    val Search: List<Movie>? = mutableListOf(),
    val totalResults: String?,
    val Response: String?
)