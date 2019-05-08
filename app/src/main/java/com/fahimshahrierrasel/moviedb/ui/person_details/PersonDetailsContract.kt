package com.fahimshahrierrasel.moviedb.ui.person_details

import com.fahimshahrierrasel.moviedb.data.model.Person
import com.fahimshahrierrasel.moviedb.data.model.PersonCreditResponse
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface PersonDetailsContract {
    interface Presenter : BasePresenter {
        // Get actor details from api
        fun getPersonDetails(personId: Int)

        // Get actor acted movies from api
        fun getKnownMovies(personId: Int)
    }

    interface View : BaseView<Presenter> {
        // Find actor id from the bundle when first created
        fun findPersonId()

        // Populate actor info from fetch data
        fun populatePersonDetails(person: Person)

        // Populate actor movies
        fun populateMovieCredits(personCreditResponse: PersonCreditResponse)
    }
}