package com.fahimshahrierrasel.moviedb.ui.about

class AboutPresenter(private val aboutView: AboutContract.View) : AboutContract.Presenter {
    init {
        aboutView.setPresenter(this)
    }

    override fun start() {
    }
}