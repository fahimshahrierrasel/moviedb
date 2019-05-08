package com.fahimshahrierrasel.moviedb.ui.movie_details

import com.fahimshahrierrasel.moviedb.data.model.CreditResponse
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface MovieDetailsContract {
    interface Presenter : BasePresenter {
        // Get movie details
        fun getMovieDetails(movieId: Int)

        // Get movie cast and crew
        fun getMovieCredits(movieId: Int)
    }

    interface View : BaseView<Presenter> {
        // Find movie id from the bundle
        fun findMovieId()

        // populate movie info the view
        fun populateMovieDetails(movie: Movie)

        // populate top 10 cast list of the movie
        fun populateCredits(creditResponse: CreditResponse)
    }
}