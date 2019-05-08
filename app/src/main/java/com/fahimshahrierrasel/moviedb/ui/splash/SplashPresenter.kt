package com.fahimshahrierrasel.moviedb.ui.splash

class SplashPresenter(private val splashView: SplashContract.View) : SplashContract.Presenter {
    init {
        splashView.setPresenter(this)
    }

    override fun start() {

    }
}