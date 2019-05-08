package com.fahimshahrierrasel.moviedb.ui.genres

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.MovieGenre
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GenrePresenter(private val genreView: GenreContract.View) : GenreContract.Presenter {
    init {
        genreView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getAllGenres() {
        ApiUtils.movieDBService.requestForMovieGenre(apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieGenre> {
                override fun onSuccess(t: MovieGenre) {
                    genreView.populateGenreRecyclerView(t.genres)
                    genreView.hideProgressView()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                    genreView.hideProgressView()
                }

            })

    }


    override fun start() {
        getAllGenres()
        genreView.showProgressView()
    }
}