package com.fahimshahrierrasel.moviedb.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.helper.GENRE_ID
import com.fahimshahrierrasel.moviedb.helper.GENRE_NAME
import com.fahimshahrierrasel.moviedb.helper.MOVIE_KEYWORD
import com.fahimshahrierrasel.moviedb.ui.adapters.MovieAdapter
import com.fahimshahrierrasel.moviedb.viewmodels.MovieViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : BaseFragment() {
    override val recyclerView: RecyclerView
        get() = rv_movies

    private lateinit var movieAdapter: MovieAdapter
    private val movieResults = ArrayList<MovieResult>()
    private var currentPage = 1

    private val movieViewModel by lazy {
        ViewModelProvider(rootActivity, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
    }

    companion object {
        fun newInstance(bundle: Bundle) = MovieListFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieKeyword = arguments?.getString(MOVIE_KEYWORD)
        val genreId = arguments?.getInt(GENRE_ID)
        val genreName = arguments?.getString(GENRE_NAME)

        rv_movies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movieResults)
        rv_movies.adapter = movieAdapter
        movieAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(movieResults[position].id)
        }
        movieAdapter.emptyView = loadingProgressView

        if (movieKeyword != null)
            movieViewModel.getMovieListWith(movieKeyword, currentPage)
        else
            movieViewModel.getGenreMovieListWith(genreId!!, currentPage)

        movieViewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
            if (currentPage == 1)
                movieResults.clear()
            movieResults.addAll(movieList.movieResults)
            movieAdapter.notifyDataSetChanged()
            movieAdapter.loadMoreComplete()
            currentPage = movieList.page
        })

        movieAdapter.setOnLoadMoreListener({
            Logger.d("Change Current Page From $currentPage to ${currentPage + 1}")
            if (movieKeyword != null)
                movieViewModel.getMovieListWith(movieKeyword, currentPage + 1)
            else
                movieViewModel.getGenreMovieListWith(genreId!!, currentPage + 1)
        }, rv_movies)
    }
}