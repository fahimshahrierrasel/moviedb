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
    suspend fun requestForMovieList(
        @Path("keyword") keyword: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): MovieList

    @GET("movie/{movie_id}")
    fun requestForMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<Movie>

    @GET("movie/{movie_id}/credits")
    fun requestForGetCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<CreditResponse>

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
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): Single<MovieList>


    @GET("person/popular")
    fun requestForPopularPersons(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Single<PersonResponse>

    @GET("person/{person_id}")
    fun requestForPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Single<Person>

    @GET("person/{person_id}/movie_credits")
    fun requestForPersonMovies(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Single<PersonCreditResponse>


}