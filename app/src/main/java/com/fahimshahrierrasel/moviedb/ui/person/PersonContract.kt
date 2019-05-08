package com.fahimshahrierrasel.moviedb.ui.person

import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.data.model.PersonResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface PersonContract {

    interface Presenter : BasePresenter {
        fun getAllPerson(page: Int = 1)
    }

    interface View : BaseView<Presenter> {
        fun populatePersonRecyclerView(personResults: List<PersonResult>)
    }
}