package com.fahimshahrierrasel.moviedb.ui.movie_details

import com.fahimshahrierrasel.moviedb.data.model.CreditResponse
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface MovieDetailsContract {
    interface Presenter : BasePresenter {
        fun getMovieDetails(movieId: Int)
        fun getMovieCredits(movieId: Int)
    }

    interface View : BaseView<Presenter> {
        fun findMovieId()
        fun populateMovieDetails(movie: Movie)
        fun populateCredits(creditResponse: CreditResponse)
    }
}