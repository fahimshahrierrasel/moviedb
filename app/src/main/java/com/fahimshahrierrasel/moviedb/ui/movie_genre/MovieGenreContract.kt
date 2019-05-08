package com.fahimshahrierrasel.moviedb.ui.movie_genre

import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface MovieGenreContract {
    interface Presenter : BasePresenter {
        // Get movie list the the same genre id
        fun getSameGenreMovies(genreId: Int, page: Int)

        // Load next movie list page
        fun loadNextPage(genreId: Int)
    }

    interface View : BaseView<Presenter> {
        // Find genre id and name from the bundle
        fun findGenreId()

        // Populate movie in recycler view
        fun populateMovieRecyclerView(movieResults: List<MovieResult>)

        // Stop loading more date to the recycler view
        fun stopLoadMore()
    }
}