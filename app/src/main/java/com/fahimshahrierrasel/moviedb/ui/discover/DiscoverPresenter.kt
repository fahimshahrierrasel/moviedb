package com.fahimshahrierrasel.moviedb.ui.discover

class DiscoverPresenter(private val genreView: DiscoverContract.View) : DiscoverContract.Presenter {
    init {
        genreView.setPresenter(this)
    }


    override fun start() {

    }
}