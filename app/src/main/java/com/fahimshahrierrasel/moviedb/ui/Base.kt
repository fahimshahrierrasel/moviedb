package com.fahimshahrierrasel.moviedb.ui

interface BasePresenter {
    fun start()
}

interface BaseView<T> {
    fun setPresenter(presenter: T)
}