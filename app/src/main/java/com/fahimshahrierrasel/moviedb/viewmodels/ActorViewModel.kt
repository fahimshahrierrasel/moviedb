package com.fahimshahrierrasel.moviedb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.fahimshahrierrasel.moviedb.data.MovieRepositoryImpl
import com.fahimshahrierrasel.moviedb.data.model.Person
import com.fahimshahrierrasel.moviedb.data.model.PersonCreditResponse
import com.fahimshahrierrasel.moviedb.data.model.PersonResponse
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers

class ActorViewModel : ViewModel() {
    private val repository: MovieRepositoryImpl = MovieRepositoryImpl()
    val personResponse = MediatorLiveData<PersonResponse>()

    fun getActorResponse(pageNumber: Int = 1) {
        Logger.d("Get Person Response Page: $pageNumber ")
        val liveData = liveData(Dispatchers.IO) {
            val retrievedPersonResponseList = repository.getPopularActors(pageNo = pageNumber)
            emit(retrievedPersonResponseList)
        }

        personResponse.addSource(liveData) {
            personResponse.value = it
            personResponse.removeSource(liveData)
        }
    }

    fun getActorDetails(actorId: Int): LiveData<Person> {
        return liveData(Dispatchers.IO) {
            val retrievedActorDetails = repository.getActorDetails(actorId)
            emit(retrievedActorDetails)
        }
    }

    fun getActorMovies(actorId: Int): LiveData<PersonCreditResponse> {
        return liveData(Dispatchers.IO) {
            val retrievedActorMovies = repository.getActorMovies(actorId)
            emit(retrievedActorMovies)
        }
    }
}
