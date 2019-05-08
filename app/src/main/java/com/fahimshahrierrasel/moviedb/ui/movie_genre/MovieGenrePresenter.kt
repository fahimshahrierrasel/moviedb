package com.fahimshahrierrasel.moviedb.ui.movie_genre

import android.graphics.pdf.PdfDocument
import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieGenrePresenter(private val movieGenreView: MovieGenreContract.View) : MovieGenreContract.Presenter {
    init {
        movieGenreView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()
    private var currentPage = 1

    override fun getSameGenreMovies(genreId: Int, page: Int) {
        ApiUtils.movieDBService.requestForGenreMovies(genreId, apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    movieGenreView.populateMovieRecyclerView(t.movieResults)
                    movieGenreView.hideProgressView()
                    movieGenreView.stopLoadMore()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                    movieGenreView.hideProgressView()
                }

            })

    }

    override fun loadNextPage(genreId: Int) {
        getSameGenreMovies(genreId, currentPage++)
    }


    override fun start() {
        movieGenreView.findGenreId()
        movieGenreView.showProgressView()
    }
}