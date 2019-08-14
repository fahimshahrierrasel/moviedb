package com.fahimshahrierrasel.moviedb.data

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.MovieGenre
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.fahimshahrierrasel.moviedb.data.model.Person
import com.fahimshahrierrasel.moviedb.data.model.PersonCreditResponse
import com.fahimshahrierrasel.moviedb.helper.apiKey

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getMovieGenres(): MovieGenre = ApiUtils.movieDBService.movieGenres(apiKey)

    override suspend fun getGenreMovies(genreId: Int, pageNo: Int): MovieList =
        ApiUtils.movieDBService.genreMovies(apiKey = apiKey, genreId = genreId, page = pageNo)

    override suspend fun getMovieList(keyword: String, pageNo: Int) =
        ApiUtils.movieDBService.listMovies(apiKey = apiKey, keyword = keyword, page = pageNo)

    override suspend fun getMovieDetails(movieId: Int) =
        ApiUtils.movieDBService.movieDetails(apiKey = apiKey, movieId = movieId)

    override suspend fun getMovieCredits(movieId: Int) =
        ApiUtils.movieDBService.movieCasts(apiKey = apiKey, movieId = movieId)

    override suspend fun getPopularActors(pageNo: Int) =
        ApiUtils.movieDBService.popularActors(apiKey = apiKey, page = pageNo)

    override suspend fun getActorDetails(actorId: Int): Person =
        ApiUtils.movieDBService.actorDetails(actorId, apiKey)

    override suspend fun getActorMovies(actorId: Int): PersonCreditResponse =
        ApiUtils.movieDBService.actorMovies(actorId, apiKey)
}