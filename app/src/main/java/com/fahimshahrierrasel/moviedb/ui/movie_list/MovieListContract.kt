package com.fahimshahrierrasel.moviedb.ui.movie_list

import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface MovieListContract {
    interface Presenter : BasePresenter {
        fun getMovieList(keyword: String="popular")
    }

    interface View : BaseView<Presenter> {
        fun findMovieKeyword()
        fun populateMovieRecyclerView(movieResults: List<MovieResult>)
    }
}