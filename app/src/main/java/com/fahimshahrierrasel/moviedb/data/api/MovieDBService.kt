package com.fahimshahrierrasel.moviedb.data.api

import com.fahimshahrierrasel.moviedb.data.model.Credit
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.data.model.MovieGenre
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {
    @GET("genre/movie/list")
    fun requestForMovieGenre(
        @Query("api_key") apiKey: String
    ): Single<MovieGenre>

    @GET("movie/popular")
    fun requestForPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Single<MovieList>

    @GET("movie/{movie_id}")
    fun requestForMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Movie>

    @GET("movie/{movie_id}/credits")
    fun requestForGetCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Credit>
}