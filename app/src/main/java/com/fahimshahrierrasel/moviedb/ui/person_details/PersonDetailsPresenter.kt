package com.fahimshahrierrasel.moviedb.ui.person_details

import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.CreditResponse
import com.fahimshahrierrasel.moviedb.data.model.Person
import com.fahimshahrierrasel.moviedb.data.model.PersonCreditResponse
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PersonDetailsPresenter(private val personDetailsView: PersonDetailsContract.View) :
    PersonDetailsContract.Presenter {

    init {
        personDetailsView.setPresenter(this)
    }

    private val compositeDisposable = CompositeDisposable()

    override fun getPersonDetails(personId: Int) {
        ApiUtils.movieDBService.requestForPersonDetails(personId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Person> {
                override fun onSuccess(t: Person) {
                    personDetailsView.populatePersonDetails(t)
                    getKnownMovies(t.id)
                    personDetailsView.hideProgressView()
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                    personDetailsView.hideProgressView()
                }

            })
    }

    override fun getKnownMovies(personId: Int) {
        ApiUtils.movieDBService.requestForPersonMovies(personId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<PersonCreditResponse> {
                override fun onSuccess(t: PersonCreditResponse) {
                    personDetailsView.populateMovieCredits(t)
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
        personDetailsView.findPersonId()
        personDetailsView.showProgressView()
    }

}