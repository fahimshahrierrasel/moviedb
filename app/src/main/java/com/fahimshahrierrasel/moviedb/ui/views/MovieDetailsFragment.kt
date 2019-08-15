package com.fahimshahrierrasel.moviedb.ui.views

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.CreditCast
import com.fahimshahrierrasel.moviedb.data.model.CreditResponse
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.helper.MOVIE_ID
import com.fahimshahrierrasel.moviedb.helper.backdropPrefix
import com.fahimshahrierrasel.moviedb.helper.posterPrefix
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.CastAdapter
import com.fahimshahrierrasel.moviedb.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.content_movie.*
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : BaseFragment() {
    override val recyclerView: RecyclerView
        get() = rv_casts

    private lateinit var castAdapter: CastAdapter
    private val casts = ArrayList<CreditCast>()
    private val movieViewModel by lazy {
        ViewModelProvider(rootActivity, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
    }

    companion object {
        fun newInstance(bundle: Bundle) = MovieDetailsFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(MOVIE_ID)

        // setting cast list
        rv_casts.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        castAdapter = CastAdapter(casts)
        rv_casts.adapter = castAdapter

        movieViewModel.getMovieDetails(movieId!!).observe(viewLifecycleOwner, Observer { movie ->
            populateMovieDetails(movie)
        })

        movieViewModel.getMovieCasts(movieId).observe(viewLifecycleOwner, Observer { creditResponse ->
            populateCredits(creditResponse)
        })

    }


    private fun populateMovieDetails(movie: Movie) {
        Glide.with(context!!)
            .load("$backdropPrefix${movie.backdropPath}")
            .into(iv_movie_backdrop)

        Glide.with(context!!)
            .load("$posterPrefix${movie.posterPath}")
            .into(iv_movie_poster)

        toolbar.title = movie.title
        toolbar.setTitleTextColor(Color.WHITE)

        tv_movie_overview.text = movie.overview
        tv_movie_status.text = movie.status
        tv_movie_release_date.text = movie.releaseDate
        tv_movie_language.text = movie.originalLanguage
        tv_movie_runtime.text = "${movie.runtime / 60}h ${movie.runtime % 60}m"
        tv_movie_budget.text = "${movie.budget / 1000000} million"
        tv_movie_revenue.text = "${movie.revenue / 1000000} million"
    }

    fun populateCredits(creditResponse: CreditResponse) {
        // Only load 10 cast(loading all cast is not efficient too much memory)
        if (creditResponse.cast.size > 10)
            casts.addAll(creditResponse.cast.subList(0, 10))
        else
            casts.addAll(creditResponse.cast)
        castAdapter.notifyDataSetChanged()
    }
}