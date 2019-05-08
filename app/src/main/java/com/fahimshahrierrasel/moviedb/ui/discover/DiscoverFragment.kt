package com.fahimshahrierrasel.moviedb.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.MovieAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : Fragment(), DiscoverContract.View {
    private lateinit var discoverPresenter: DiscoverContract.Presenter
    private lateinit var movieAdapter: MovieAdapter
    private val movies = ArrayList<MovieResult>()
    private lateinit var rootActivity: MainActivity
    private var isAdvanceSearchShown = false

    companion object {
        fun newInstance(bundle: Bundle) = DiscoverFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = "Discover Movies"

        iv_show_hide.setOnClickListener {
            isAdvanceSearchShown = !isAdvanceSearchShown
            if (isAdvanceSearchShown)
                ll_advance_search.visibility = View.VISIBLE
            else
                ll_advance_search.visibility = View.GONE
        }

        rs_rating.getThumb(0).value = 5
        rs_rating.getThumb(1).value = 10

        rs_duration.getThumb(0).value = 70
        rs_duration.getThumb(1).value = 200

        tv_rating.text = "Rating: 5 - 10"
        tv_duration.text = "Duration: 70 - 200"

        rs_rating.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            val min = multiSlider.getThumb(0).value
            val max = multiSlider.getThumb(1).value

            tv_rating.text = "Rating: $min - $max"
        }

        rs_duration.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            val min = multiSlider.getThumb(0).value
            val max = multiSlider.getThumb(1).value

            tv_duration.text = "Duration: $min - $max"
        }

        val years = ArrayList<String>()
        for (year in 2019 downTo 1901 step 1)
            years.add(year.toString())

        val yearAdapter = ArrayAdapter<String>(rootActivity, android.R.layout.simple_spinner_dropdown_item, years)
        spinner_year.adapter = yearAdapter

        btn_search.setOnClickListener {
            val query = et_movie_name.text.toString()
            discoverPresenter.searchMovies(query)
        }

        btn_advance_search.setOnClickListener {
            val voteGte = rs_rating.getThumb(0).value
            val voteLte = rs_rating.getThumb(1).value
            val runtimeGte = rs_duration.getThumb(0).value
            val runtimeLte = rs_duration.getThumb(1).value
            val year = spinner_year.selectedItem.toString()

            discoverPresenter.discoverMovies(year.toInt(), voteGte, voteLte, runtimeGte, runtimeLte)
        }

        rv_movies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movies)
        rv_movies.adapter = movieAdapter
        movieAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(movies[position].id)
        }
    }

    override fun showInputWarning(message: String) {
        Toast.makeText(rootActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun appendDiscoveredMovies(movieResults: List<MovieResult>) {
        movies.addAll(movieResults)
        movieAdapter.notifyDataSetChanged()
    }

    override fun addDiscoveredMovies(movieResults: List<MovieResult>) {
        movies.clear();
        movies.addAll(movieResults)
        movieAdapter.notifyDataSetChanged()
    }


    override fun onStart() {
        super.onStart()
        discoverPresenter.start()
    }

    override fun setPresenter(presenter: DiscoverContract.Presenter) {
        discoverPresenter = presenter
    }
}