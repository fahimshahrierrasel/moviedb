package com.fahimshahrierrasel.moviedb.ui.movie_details

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.Credit
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsPresenter(private val movieDetailView: MovieDetailsContract.View) : MovieDetailsContract.Presenter {

    init {
        movieDetailView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getMovieDetails(movieId: Int) {

        ApiUtils.movieDBService.requestForMovie(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Movie> {
                override fun onSuccess(t: Movie) {
                    movieDetailView.populateMovieDetails(t)
                    getMovieCredits(movieId)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                }

            })
    }

    override fun getMovieCredits(movieId: Int) {
        ApiUtils.movieDBService.requestForGetCredits(movieId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Credit> {
                override fun onSuccess(t: Credit) {
                    movieDetailView.populateCredits(t)
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
        movieDetailView.findMovieId()
    }

}