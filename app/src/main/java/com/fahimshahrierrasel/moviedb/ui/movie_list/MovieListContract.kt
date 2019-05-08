package com.fahimshahrierrasel.moviedb.ui.movie_list

import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface MovieListContract {
    interface Presenter : BasePresenter {
        // Get movie list with these keyword (like popular, top rated, etc) with page
        fun getMovieList(keyword: String, page: Int)

        // Load next page with this keyword
        fun loadNextPage(keyword: String = "popular")
    }

    interface View : BaseView<Presenter> {
        // Get movie keyword from the bundle
        fun findMovieKeyword()

        // Populate movie list
        fun populateMovieRecyclerView(movieResults: List<MovieResult>)

        // Stop loading more movie to the list
        fun stopLoadMore()
    }
}