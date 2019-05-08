package com.fahimshahrierrasel.moviedb.ui.discover

import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface DiscoverContract {

    interface Presenter : BasePresenter {
        fun getSearchedMovies(query: String, page: Int = 1)
        fun getDiscoverMovies(
            releaseYear: Int = 2019,
            voteGte: Int = 5,
            voteLte: Int = 10,
            runtimeGte: Int = 70,
            runtimeLte: Int = 200,
            page: Int = 1
        )

        fun searchMovies(query: String)
        fun discoverMovies(
            releaseYear: Int,
            voteGte: Int,
            voteLte: Int,
            runtimeGte: Int,
            runtimeLte: Int
        )
    }

    interface View : BaseView<Presenter> {
        fun appendDiscoveredMovies(movieResults: List<MovieResult>)
        fun addDiscoveredMovies(movieResults: List<MovieResult>)
        fun showInputWarning(message: String)
    }
}