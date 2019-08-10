package com.fahimshahrierrasel.moviedb.data

import com.fahimshahrierrasel.moviedb.data.model.CreditResponse
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.data.model.MovieList

interface MovieRepository {
    suspend fun getMovieList(keyword: String = "popular", pageNo: Int = 1): MovieList
    suspend fun getMovieDetails(movieId: Int): Movie
    suspend fun getMovieCredits(movieId: Int): CreditResponse
}