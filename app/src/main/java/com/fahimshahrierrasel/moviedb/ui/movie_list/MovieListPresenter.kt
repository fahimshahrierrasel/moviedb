package com.fahimshahrierrasel.moviedb.ui.movie_list

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieListPresenter(private val movieListView: MovieListContract.View) : MovieListContract.Presenter {
    init {
        movieListView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getMovieList(keyword: String) {
        ApiUtils.movieDBService.requestForMovieList(keyword, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    movieListView.populateMovieRecyclerView(t.movieResults)
                    movieListView.hideProgressView()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                    movieListView.hideProgressView()
                }

            })

    }


    override fun start() {
        movieListView.findMovieKeyword()
        movieListView.showProgressView()
    }
}