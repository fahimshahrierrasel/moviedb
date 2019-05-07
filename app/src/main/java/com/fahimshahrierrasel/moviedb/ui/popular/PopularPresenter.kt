package com.fahimshahrierrasel.moviedb.ui.popular

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PopularPresenter(private val popularView: PopularContract.View) : PopularContract.Presenter {
    init {
        popularView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getPopularMovies() {
        ApiUtils.movieDBService.requestForPopularMovies(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    popularView.populateMovieRecyclerview(t.movieResults)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                }

            })

    }


    override fun start() {
        getPopularMovies()
    }
}