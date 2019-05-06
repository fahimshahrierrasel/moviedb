package com.fahimshahrierrasel.moviedb.ui.popular

class PopularPresenter(private val popularView: PopularContract.View) : PopularContract.Presenter {
    init {
        popularView.setPresenter(this)
    }

    override fun start() {
        //
    }
}