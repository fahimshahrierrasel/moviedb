package com.fahimshahrierrasel.moviedb.data

import com.fahimshahrierrasel.moviedb.data.model.*

interface MovieRepository {
    suspend fun getMovieGenres(): MovieGenre
    suspend fun getMovieList(keyword: String = "popular", pageNo: Int = 1): MovieList
    suspend fun getMovieDetails(movieId: Int): Movie
    suspend fun getMovieCredits(movieId: Int): CreditResponse
    suspend fun getPopularActors(pageNo: Int): PersonResponse
    suspend fun getActorDetails(actorId: Int): Person
    suspend fun getActorMovies(actorId: Int): PersonCreditResponse
}