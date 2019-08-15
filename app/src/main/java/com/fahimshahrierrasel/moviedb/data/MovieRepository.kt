package com.fahimshahrierrasel.moviedb.data

import com.fahimshahrierrasel.moviedb.data.model.*
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovieGenres(): MovieGenre
    suspend fun getMovieList(keyword: String = "popular", pageNo: Int = 1): Response<MovieList>
    suspend fun getMovieDetails(movieId: Int): Movie
    suspend fun getMovieCredits(movieId: Int): CreditResponse
    suspend fun getPopularActors(pageNo: Int): PersonResponse
    suspend fun getActorDetails(actorId: Int): Person
    suspend fun getActorMovies(actorId: Int): PersonCreditResponse
    suspend fun getGenreMovies(genreId: Int, pageNo: Int = 1): MovieList
    suspend fun searchMovies(query: String, pageNo: Int = 1): MovieList
    suspend fun advanceSearch(
        releaseYear: Int,
        voteGte: Int,
        voteLte: Int,
        runtimeGte: Int,
        runtimeLte: Int,
        pageNo: Int = 1
    ): MovieList
}