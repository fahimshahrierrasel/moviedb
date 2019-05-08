package com.fahimshahrierrasel.moviedb.ui.person

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.PersonResponse
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PersonPresenter(private val personView: PersonContract.View) : PersonContract.Presenter {
    init {
        personView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getAllPerson(page: Int) {
        ApiUtils.movieDBService.requestForPopularPersons(apiKey, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<PersonResponse> {
                override fun onSuccess(t: PersonResponse) {
                    Logger.d(t)
                    personView.populatePersonRecyclerView(t.results)
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
        getAllPerson()
    }
}