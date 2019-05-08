package com.fahimshahrierrasel.moviedb.ui.movie_genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.helper.GENRE_ID
import com.fahimshahrierrasel.moviedb.helper.GENRE_NAME
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieGenreFragment : Fragment(), MovieGenreContract.View {

    private lateinit var movieGenrePresenter: MovieGenreContract.Presenter
    private lateinit var movieAdapter: MovieAdapter
    private val movieResults = ArrayList<MovieResult>()
    private lateinit var rootActivity: MainActivity

    companion object {
        fun newInstance(bundle: Bundle) = MovieGenreFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(GENRE_NAME, "Movies").apply {
            toolbar.title = this?.capitalize()
        }

        // Setting movie recycler view
        rv_movies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movieResults)
        rv_movies.adapter = movieAdapter

        // Movie item click will open movie details
        movieAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(movieResults[position].id)
        }

        // getting genre id from bundle and load more data event
        val genreId = arguments?.getInt(GENRE_ID)
        movieAdapter.setOnLoadMoreListener({
            movieGenrePresenter.loadNextPage(genreId!!)
        }, rv_movies)
    }

    override fun stopLoadMore() {
        movieAdapter.loadMoreComplete()
    }

    override fun showProgressView() {
        rootActivity.progressView.show()
    }

    override fun hideProgressView() {
        rootActivity.progressView.hide()
    }

    override fun populateMovieRecyclerView(movieResults: List<MovieResult>) {
        this.movieResults.addAll(movieResults)
        movieAdapter.notifyDataSetChanged()
    }

    override fun findGenreId() {
        val genreId = arguments?.getInt(GENRE_ID)
        movieGenrePresenter.loadNextPage(genreId!!)
    }

    override fun onStart() {
        super.onStart()
        movieGenrePresenter.start()
    }

    override fun setPresenter(presenter: MovieGenreContract.Presenter) {
        movieGenrePresenter = presenter
    }
}