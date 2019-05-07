package com.fahimshahrierrasel.moviedb.data.api

import com.fahimshahrierrasel.moviedb.helper.API_BASE_URL

object ApiUtils {
    val movieDBService: MovieDBService = RetrofitClient.getClient(API_BASE_URL).create(
        MovieDBService::class.java)
}