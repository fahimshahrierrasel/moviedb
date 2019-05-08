package com.fahimshahrierrasel.moviedb.ui.discover

import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface DiscoverContract {

    interface Presenter : BasePresenter {
        // Search movies with query from api
        fun getSearchedMovies(query: String, page: Int = 1)

        // Discover movies with year, rating and duration from api
        fun getDiscoverMovies(
            releaseYear: Int,
            voteGte: Int,
            voteLte: Int,
            runtimeGte: Int,
            runtimeLte: Int,
            page: Int = 1
        )

        // search movie helper and query validator
        fun searchMovies(query: String)

        // discover movie helper
        fun discoverMovies(
            releaseYear: Int = 2019,
            voteGte: Int = 5,
            voteLte: Int = 10,
            runtimeGte: Int = 70,
            runtimeLte: Int = 200
        )

        // Load next page of the movies based on condition
        fun loadNextPage()
    }

    interface View : BaseView<Presenter> {
        // Append movie to the current movie list
        fun appendDiscoveredMovies(movieResults: List<MovieResult>)

        // Add movies to the empty list
        fun addDiscoveredMovies(movieResults: List<MovieResult>)

        // Show toast warning
        fun showInputWarning(message: String)

        // Stop Loading more data
        fun stopLoadMore()

        // No more data to load
        fun noLoadMore()
    }
}