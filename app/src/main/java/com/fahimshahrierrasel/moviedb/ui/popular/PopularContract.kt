package com.fahimshahrierrasel.moviedb.ui.popular

import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface PopularContract {
    interface Presenter : BasePresenter {
        fun getPopularMovies()
    }

    interface View : BaseView<Presenter> {
        fun populateMovieRecyclerview(movieResults: List<MovieResult>)
    }
}