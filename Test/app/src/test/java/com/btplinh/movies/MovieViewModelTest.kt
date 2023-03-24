package com.btplinh.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.btplinh.domain.basenetwork.NetworkStatus
import com.btplinh.domain.entities.Movie
import com.btplinh.domain.usecase.MovieUseCase
import com.btplinh.movies.ui.movie.MovieViewModel
import com.btplinh.movies.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    private val movieUseCase = mockk<MovieUseCase>()

    @Before
    fun setup() {
        viewModel = MovieViewModel(
            movieUseCase
        )
    }

    @Test
    fun getForeCaseWeather_Case01() {
        val page = 1
        val name = ""
        val response = listOf(
            Movie(Title = "", Year = "", imdbID = "", Type = "", Poster = "")
        )

        coEvery {
            movieUseCase.execute(name, page)
        } returns flow {
            emit(NetworkStatus.Success(response))
        }
        viewModel.getListMovie(page, name)
        assert(viewModel.listMovieLiveData.getOrAwaitValue().isEmpty())
    }
}