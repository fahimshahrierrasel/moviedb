package com.fahimshahrierrasel.moviedb.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.fahimshahrierrasel.moviedb.data.MovieRepositoryImpl
import com.fahimshahrierrasel.moviedb.data.model.PersonResponse
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers

class ActorViewModel: ViewModel() {
    private val repository: MovieRepositoryImpl = MovieRepositoryImpl()
    val personResponse = MediatorLiveData<PersonResponse>()

    fun getActorResponse(pageNumber: Int = 1) {
        Logger.d("Get Person Response Page: $pageNumber ")
        val liveData = liveData(Dispatchers.IO) {
            val retriedPersonResponseList = repository.getPopularPersons(pageNo = pageNumber)
            emit(retriedPersonResponseList)
        }

        personResponse.addSource(liveData){
            personResponse.value = it
            personResponse.removeSource(liveData)
        }
    }
}
