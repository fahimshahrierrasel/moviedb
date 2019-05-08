package com.fahimshahrierrasel.moviedb.ui.movie_list

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
import com.fahimshahrierrasel.moviedb.helper.MOVIE_KEYWORD
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.MovieAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment(), MovieListContract.View {
    private lateinit var movieListPresenter: MovieListContract.Presenter
    private lateinit var movieAdapter: MovieAdapter
    private val movieResults = ArrayList<MovieResult>()
    private lateinit var rootActivity: MainActivity

    companion object {
        fun newInstance(bundle: Bundle) = MovieListFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootActivity = activity as MainActivity

        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieKeyword = arguments?.getString(MOVIE_KEYWORD, "popular")

        rv_movies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movieResults)
        rv_movies.adapter = movieAdapter
        movieAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(movieResults[position].id)
        }

        movieAdapter.setOnLoadMoreListener({
            movieListPresenter.loadNextPage(movieKeyword!!)
        }, rv_movies)
    }

    override fun showProgressView() {
        rootActivity.progressView.show()
    }

    override fun hideProgressView() {
        rootActivity.progressView.hide()
    }

    override fun stopLoadMore() {
        movieAdapter.loadMoreComplete()
    }

    override fun populateMovieRecyclerView(movieResults: List<MovieResult>) {
        this.movieResults.addAll(movieResults)
        movieAdapter.notifyDataSetChanged()
    }

    override fun findMovieKeyword() {
        val movieKeyword = arguments?.getString(MOVIE_KEYWORD, "popular")
        movieKeyword?.apply {
            movieListPresenter.loadNextPage(this)
        }.also {
            it!!.replace("_", " ").toUpperCase().apply {
                toolbar.title = "$this MOVIES"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        movieListPresenter.start()
    }

    override fun setPresenter(presenter: MovieListContract.Presenter) {
        movieListPresenter = presenter
    }
}