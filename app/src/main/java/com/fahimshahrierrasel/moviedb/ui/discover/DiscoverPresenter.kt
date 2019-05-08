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
    init {
        discoverView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()
    private var currentPage = 1
    private var mode = ""
    private var totalPage = 2
    private var searchQuery = ""
    private var releaseYear = 0
    private var voteGte = 0
    private var voteLte = 0
    private var runtimeGte = 0
    private var runtimeLte = 0

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
            runtimeLte,
            page = page
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    totalPage = t.totalPages
                    if (page <= 1)
                        discoverView.addDiscoveredMovies(t.movieResults)
                    else
                        discoverView.appendDiscoveredMovies(t.movieResults)

                    discoverView.hideProgressView()
                    discoverView.stopLoadMore()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                    discoverView.hideProgressView()
                }

            })
    }

    override fun getSearchedMovies(query: String, page: Int) {
        ApiUtils.movieDBService.requestForSearchMovies(apiKey, query, page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    totalPage = t.totalPages
                    if (page <= 1)
                        discoverView.addDiscoveredMovies(t.movieResults)
                    else
                        discoverView.appendDiscoveredMovies(t.movieResults)

                    discoverView.hideProgressView()
                    discoverView.stopLoadMore()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                    discoverView.hideProgressView()
                }

            })
    }

    override fun searchMovies(query: String) {
        if (query.isEmpty())
            discoverView.showInputWarning("Please input something to search!!")
        else {
            if (mode != "search") {
                mode = "search"
                currentPage = 1
                totalPage = 2
            }
            discoverView.showProgressView()
            searchQuery = query
            loadNextPage()
        }
    }

    override fun discoverMovies(releaseYear: Int, voteGte: Int, voteLte: Int, runtimeGte: Int, runtimeLte: Int) {
        if (mode != "discover") {
            mode = "discover"
            currentPage = 1
            totalPage = 2
        }
        discoverView.showProgressView()
        this.releaseYear = releaseYear
        this.voteGte = voteGte
        this.voteLte = voteLte
        this.runtimeGte = runtimeGte
        this.runtimeLte = runtimeLte
        loadNextPage()
    }

    override fun loadNextPage() {
        when (mode) {
            "search" -> {
                if (currentPage < totalPage)
                    getSearchedMovies(searchQuery, currentPage++)
                else
                    discoverView.noLoadMore()
            }
            "discover" -> {
                if (currentPage < totalPage)
                    getDiscoverMovies(releaseYear, voteGte, voteLte, runtimeGte, runtimeLte, currentPage++)
                else
                    discoverView.noLoadMore()
            }
        }
    }

    override fun start() {
        discoverMovies()
        discoverView.showProgressView()
    }
}