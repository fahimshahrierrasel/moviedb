package com.fahimshahrierrasel.moviedb.data

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.helper.apiKey

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getMovieList(keyword: String, pageNo: Int) =
        ApiUtils.movieDBService.listMovies(apiKey = apiKey, keyword = keyword, page = pageNo)

    override suspend fun getMovieDetails(movieId: Int) =
        ApiUtils.movieDBService.movieDetails(apiKey = apiKey, movieId = movieId)

    override suspend fun getMovieCredits(movieId: Int) =
        ApiUtils.movieDBService.movieCasts(apiKey = apiKey, movieId = movieId)

    override suspend fun getPopularPersons(pageNo: Int) =
        ApiUtils.movieDBService.popularActors(apiKey = apiKey, page = pageNo)
}