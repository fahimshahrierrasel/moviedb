package com.fahimshahrierrasel.moviedb.viewmodels

import androidx.lifecycle.*
import com.fahimshahrierrasel.moviedb.data.MovieRepositoryImpl
import com.fahimshahrierrasel.moviedb.data.model.CreditResponse
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.data.model.MovieGenre
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers

class MovieViewModel : ViewModel() {
    private val repository: MovieRepositoryImpl = MovieRepositoryImpl()
    val movieList = MediatorLiveData<MovieList>()
    val errorMessage = MutableLiveData<String>()

    fun getMovieListWith(keyword: String, pageNumber: Int) {
        Logger.d("Get Movie List With For $keyword Page: $pageNumber ")
        val liveData = liveData(Dispatchers.IO) {
            val retriedMovieList = repository.getMovieList(keyword = keyword, pageNo = pageNumber)
            emit(retriedMovieList)
        }

        movieList.addSource(liveData) {
            Logger.d(it)
            if(it.code() == 200)
                movieList.value = it.body()
            else {
                Logger.d(it.errorBody())
                errorMessage.value = it.message()
            }
            movieList.removeSource(liveData)
        }

    }

    fun getGenreMovieListWith(genreId: Int, pageNumber: Int) {
        Logger.d("Get Movie List With For Genre Id $genreId Page: $pageNumber ")
        val liveData = liveData(Dispatchers.IO) {
            val retriedMovieList = repository.getGenreMovies(genreId = genreId, pageNo = pageNumber)
            emit(retriedMovieList)
        }

        movieList.addSource(liveData) {
            movieList.value = it
            movieList.removeSource(liveData)
        }
    }

    fun getSearchedMovies(query: String, pageNumber: Int) {
        Logger.d("Get Movie List With For Query $query Page: $pageNumber ")
        val liveData = liveData(Dispatchers.IO) {
            val retriedMovieList = repository.searchMovies(query = query, pageNo = pageNumber)
            emit(retriedMovieList)
        }

        movieList.addSource(liveData) {
            movieList.value = it
            movieList.removeSource(liveData)
        }
    }

    fun getAdvancedSearchedMovies(
        releaseYear: Int,
        voteGte: Int,
        voteLte: Int,
        runtimeGte: Int,
        runtimeLte: Int,
        pageNumber: Int
    ) {
        Logger.d("Get Movie List With For Release Year: $releaseYear, VoteGTE: $voteGte, VoteLTE: $voteLte, RuntimeGTE: $runtimeGte, RuntimeLTE: $runtimeLte, Page: $pageNumber ")
        val liveData = liveData(Dispatchers.IO) {
            val retriedMovieList = repository.advanceSearch(
                releaseYear = releaseYear,
                voteGte = voteGte,
                voteLte = voteLte,
                runtimeGte = runtimeGte,
                runtimeLte = runtimeLte,
                pageNo = pageNumber
            )


            emit(retriedMovieList)
        }

        movieList.addSource(liveData) {
            movieList.value = it
            movieList.removeSource(liveData)
        }
    }

    fun getMovieDetails(movieId: Int): LiveData<Movie> {
        return liveData(Dispatchers.IO) {
            val movieDetails = repository.getMovieDetails(movieId)
            emit(movieDetails)
        }
    }

    fun getMovieCasts(movieId: Int): LiveData<CreditResponse> {
        return liveData(Dispatchers.IO) {
            val movieCasts = repository.getMovieCredits(movieId)
            emit(movieCasts)
        }
    }

    fun getMovieGenres(): LiveData<MovieGenre> {
        return liveData(Dispatchers.IO) {
            val genres = repository.getMovieGenres()
            emit(genres)
        }
    }

}