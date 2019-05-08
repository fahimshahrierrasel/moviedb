package com.fahimshahrierrasel.moviedb.ui.person_details

import com.fahimshahrierrasel.moviedb.data.model.Person
import com.fahimshahrierrasel.moviedb.data.model.PersonCreditResponse
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface PersonDetailsContract {
    interface Presenter : BasePresenter {
        fun getPersonDetails(personId: Int)
        fun getKnownMovies(personId: Int)
    }

    interface View : BaseView<Presenter> {
        fun findPersonId()
        fun populatePersonDetails(person: Person)
        fun populateMovieCredits(personCreditResponse: PersonCreditResponse)
    }
}