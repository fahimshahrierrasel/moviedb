package com.fahimshahrierrasel.moviedb.ui.discover

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DiscoverPresenter(private val discoverView: DiscoverContract.View) : DiscoverContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun getDiscoverMovies(
        releaseYear: Int,
        voteGte: Int,
        voteLte: Int,
        runtimeGte: Int,
        runtimeLte: Int,
        page: Int
    ) {
        ApiUtils.movieDBService.requestForDiscoveredMovies(
            apiKey,
            releaseYear,
            voteGte,
            voteLte,
            runtimeGte,
            runtimeLte
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    if (page <= 1)
                        discoverView.addDiscoveredMovies(t.movieResults)
                    else
                        discoverView.appendDiscoveredMovies(t.movieResults)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                }

            })
    }

    override fun getSearchedMovies(query: String, page: Int) {
        ApiUtils.movieDBService.requestForSearchMovies(apiKey, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    if (page <= 1)
                        discoverView.addDiscoveredMovies(t.movieResults)
                    else
                        discoverView.appendDiscoveredMovies(t.movieResults)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                }

            })
    }

    override fun searchMovies(query: String) {
        if (query.isEmpty())
            discoverView.showInputWarning("Please input something to search!!")
        else
            getSearchedMovies(query)
    }

    override fun discoverMovies(releaseYear: Int, voteGte: Int, voteLte: Int, runtimeGte: Int, runtimeLte: Int) {
        getDiscoverMovies(releaseYear, voteGte, voteLte, runtimeGte, runtimeLte)
    }

    init {
        discoverView.setPresenter(this)
    }

    override fun start() {
        getDiscoverMovies()
    }
}