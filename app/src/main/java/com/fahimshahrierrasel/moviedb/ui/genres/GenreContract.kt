package com.fahimshahrierrasel.moviedb.ui.genres

import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface GenreContract {

    interface Presenter : BasePresenter {
        fun getAllGenres()
    }

    interface View : BaseView<Presenter> {
        fun populateGenreRecyclerView(genres: List<Genre>)
    }
}