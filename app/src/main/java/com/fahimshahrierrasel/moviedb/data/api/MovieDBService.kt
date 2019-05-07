package com.fahimshahrierrasel.moviedb.data.api

import com.fahimshahrierrasel.moviedb.data.model.MovieGenre
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBService {
    @GET("genre/movie/list")
    fun getMovieGenres(
        @Query("api_key") apiKey: String
    ): Single<MovieGenre>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Single<MovieList>
}