package com.fahimshahrierrasel.moviedb.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Cast
import com.fahimshahrierrasel.moviedb.data.model.Credit
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.helper.MOVIE_ID
import com.fahimshahrierrasel.moviedb.helper.backdropPrefix
import com.fahimshahrierrasel.moviedb.ui.adapters.CastAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.content_movie.*
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment(), MovieDetailsContract.View {

    private lateinit var movieDetailsPresenter: MovieDetailsContract.Presenter
    private lateinit var castAdapter: CastAdapter
    private val casts = ArrayList<Cast>()

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
        rv_casts.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        castAdapter = CastAdapter(casts)
        rv_casts.adapter = castAdapter
    }

    override fun findMovieId() {
        val movieId = arguments?.getInt(MOVIE_ID)
        movieDetailsPresenter.getMovieDetails(movieId!!)
    }

    override fun populateMovieDetails(movie: Movie) {
        Glide.with(context!!)
            .load("$backdropPrefix${movie.backdropPath}")
            .into(iv_movie_backdrop)

        tv_movie_overview.text = movie.overview
        tv_movie_status.text = movie.status
        tv_movie_release_date.text = movie.releaseDate
        tv_movie_language.text = movie.originalLanguage
        tv_movie_runtime.text = "${movie.runtime / 60}h ${movie.runtime % 60}m"
        tv_movie_budget.text = "${movie.budget / 1000000} million"
        tv_movie_revenue.text = "${movie.revenue / 1000000} million"
    }

    override fun populateCredits(credit: Credit) {
        casts.addAll(credit.cast.subList(0, 10))
        castAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        movieDetailsPresenter.start()
    }

    override fun setPresenter(presenter: MovieDetailsContract.Presenter) {
        movieDetailsPresenter = presenter
    }
}