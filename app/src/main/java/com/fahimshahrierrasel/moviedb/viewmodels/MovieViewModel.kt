package com.fahimshahrierrasel.moviedb.viewmodels

import androidx.lifecycle.*
import com.fahimshahrierrasel.moviedb.data.MovieRepositoryImpl
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers

class MovieViewModel : ViewModel() {
    private val repository: MovieRepositoryImpl = MovieRepositoryImpl()
    val movieList = MediatorLiveData<MovieList>()

    fun getMovieListWith(keyword: String, pageNumber: Int) {
        Logger.d("Get Movie List With For $keyword Page: $pageNumber ")
        val liveData = liveData(Dispatchers.IO) {
            val retriedMovieList = repository.getMovieList(keyword = keyword, pageNo = pageNumber)
            emit(retriedMovieList)
        }

        movieList.addSource(liveData){
            movieList.value = it
            movieList.removeSource(liveData)
        }
    }

}