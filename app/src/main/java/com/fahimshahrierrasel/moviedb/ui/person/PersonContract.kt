package com.fahimshahrierrasel.moviedb.ui.person

import com.fahimshahrierrasel.moviedb.data.model.PersonResult
import com.fahimshahrierrasel.moviedb.ui.BasePresenter
import com.fahimshahrierrasel.moviedb.ui.BaseView

interface PersonContract {

    interface Presenter : BasePresenter {
        // Get all popular actor
        fun getAllPerson(page: Int)

        // Load next page of the actor
        fun loadNextPage()
    }

    interface View : BaseView<Presenter> {
        // Populate actor list with fetched data
        fun populatePersonRecyclerView(personResults: List<PersonResult>)

        // Stop data fetch
        fun stopLoadMore()
    }
}