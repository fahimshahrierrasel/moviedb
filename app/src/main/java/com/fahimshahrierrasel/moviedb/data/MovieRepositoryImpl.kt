package com.fahimshahrierrasel.moviedb.data

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.helper.apiKey

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getMovieList(keyword: String, pageNo: Int) =
        ApiUtils.movieDBService.requestForMovieList(keyword = keyword, page = pageNo, apiKey = apiKey)

    override suspend fun getMovieDetails(movieId: Int) = ApiUtils.movieDBService.requestForMovie(movieId, apiKey)

    override suspend fun getMovieCredits(movieId: Int) = ApiUtils.movieDBService.requestForGetCredits(movieId, apiKey)
}