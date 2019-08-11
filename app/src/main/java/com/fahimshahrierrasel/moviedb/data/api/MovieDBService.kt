package com.fahimshahrierrasel.moviedb.data.api

import com.fahimshahrierrasel.moviedb.data.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBService {
    @GET("genre/movie/list")
    fun requestForMovieGenre(
        @Query("api_key") apiKey: String
    ): Single<MovieGenre>

    @GET("movie/{keyword}")
    suspend fun listMovies(
        @Path("keyword") keyword: String,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String
    ): MovieList

    @GET("movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):  Movie

    @GET("movie/{movie_id}/credits")
    suspend fun movieCasts(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): CreditResponse

    @GET("genre/{genre_id}/movies")
    fun requestForGenreMovies(
        @Path("genre_id") genreId: Int,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Single<MovieList>

    @GET("discover/movie")
    fun requestForDiscoveredMovies(
        @Query("api_key") apiKey: String,
        @Query("primary_release_year") releaseYear: Int,
        @Query("vote_average.gte") voteAverageGte: Int,
        @Query("vote_average.lte") voteAverageLte: Int,
        @Query("with_runtime.gte") runtimeGte: Int,
        @Query("with_runtime.lte") runtimeLte: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): Single<MovieList>

    @GET("search/movie")
    fun requestForSearchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Single<MovieList>


    @GET("person/popular")
    suspend fun popularActors(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PersonResponse

    @GET("person/{person_id}")
    suspend fun actorDetails(
        @Path("person_id") actorId: Int,
        @Query("api_key") apiKey: String
    ): Person

    @GET("person/{person_id}/movie_credits")
    suspend fun actorMovies(
        @Path("person_id") actorId: Int,
        @Query("api_key") apiKey: String
    ): PersonCreditResponse
}