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
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.MovieAdapter
import kotlinx.android.synthetic.main.fragment_popular.*

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
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movieResults)
        rv_movies.adapter = movieAdapter
        movieAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(movieResults[position].id)
        }
    }

    override fun populateMovieRecyclerView(movieResults: List<MovieResult>) {
        this.movieResults.addAll(movieResults)
        movieAdapter.notifyDataSetChanged()
    }

    override fun findGenreId() {
        val movieId = arguments?.getInt(GENRE_ID)
        movieGenrePresenter.getSameGenreMovies(movieId!!)
    }

    override fun onStart() {
        super.onStart()
        movieGenrePresenter.start()
    }

    override fun setPresenter(presenter: MovieGenreContract.Presenter) {
        movieGenrePresenter = presenter
    }
}