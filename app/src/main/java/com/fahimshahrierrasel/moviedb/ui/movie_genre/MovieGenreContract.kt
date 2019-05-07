package com.fahimshahrierrasel.moviedb.ui.movie_genre

import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface MovieGenreContract {
    interface Presenter : BasePresenter {
        fun getSameGenreMovies(genreId: Int)
    }

    interface View : BaseView<Presenter> {
        fun findGenreId()
        fun populateMovieRecyclerView(movieResults: List<MovieResult>)
    }
}