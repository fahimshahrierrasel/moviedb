package com.fahimshahrierrasel.moviedb.data

import androidx.lifecycle.LiveData
import com.fahimshahrierrasel.moviedb.data.model.MovieList

interface MovieRepository {
    suspend fun getMovieList(keyword: String = "popular", pageNo: Int = 1): MovieList
}