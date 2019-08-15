package com.fahimshahrierrasel.moviedb.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.helper.SearchMode
import com.fahimshahrierrasel.moviedb.ui.adapters.MovieAdapter
import com.fahimshahrierrasel.moviedb.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_discover.rv_movies
import kotlinx.android.synthetic.main.fragment_discover.toolbar

class DiscoverMoviesFragment : BaseFragment() {
    override val recyclerView: RecyclerView
        get() = rv_movies
    private lateinit var movieAdapter: MovieAdapter
    private val movies = ArrayList<MovieResult>()
    private var isAdvanceSearchShown = false
    private var currentPage = 1
    private var totalPage = 1
    private var searchMode = SearchMode.NORMAL
    private var year = "2019"
    private var runtimeLte = 70
    private var runtimeGte = 200
    private var voteLte = 5
    private var voteGte = 10
    private var query = ""

    companion object {
        fun newInstance(bundle: Bundle) = DiscoverMoviesFragment().apply {
            arguments = bundle
        }
    }

    private val movieViewModel by lazy {
        ViewModelProvider(rootActivity, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = "Discover Movies"

        // Advance search show and hide event
        iv_show_hide.setOnClickListener {
            isAdvanceSearchShown = !isAdvanceSearchShown
            if (isAdvanceSearchShown)
                ll_advance_search.visibility = View.VISIBLE
            else
                ll_advance_search.visibility = View.GONE
        }

        // setting default movie rating
        rs_rating.getThumb(0).value = 5
        rs_rating.getThumb(1).value = 10
        tv_rating.text = getString(R.string.rating_label, 5, 10)

        // setting default movie duration
        rs_duration.getThumb(0).value = 70
        rs_duration.getThumb(1).value = 200
        tv_duration.text = getString(R.string.duration_label, 70, 200)

        // rating slider change listener
        rs_rating.setOnThumbValueChangeListener { multiSlider, _, _, _ ->
            val min = multiSlider.getThumb(0).value
            val max = multiSlider.getThumb(1).value

            tv_rating.text = getString(R.string.rating_label, min, max)
        }

        // duration slider change listener
        rs_duration.setOnThumbValueChangeListener { multiSlider, _, _, _ ->
            val min = multiSlider.getThumb(0).value
            val max = multiSlider.getThumb(1).value

            tv_duration.text = getString(R.string.duration_label, min, max)
        }

        // populating year spinner
        val years = ArrayList<String>()
        for (year in 2019 downTo 1901 step 1)
            years.add(year.toString())

        val yearAdapter = ArrayAdapter(rootActivity, android.R.layout.simple_spinner_dropdown_item, years)
        spinner_year.adapter = yearAdapter

        // Search button click listener
        btn_search.setOnClickListener {
            searchMode = SearchMode.NORMAL
            currentPage = 1

            query = et_movie_name.text.toString()
            movieViewModel.getSearchedMovies(query, currentPage)
        }

        // advance search button click listener
        btn_advance_search.setOnClickListener {
            searchMode = SearchMode.NORMAL
            currentPage = 1

            voteGte = rs_rating.getThumb(0).value
            voteLte = rs_rating.getThumb(1).value
            runtimeGte = rs_duration.getThumb(0).value
            runtimeLte = rs_duration.getThumb(1).value
            year = spinner_year.selectedItem.toString()

            movieViewModel.getAdvancedSearchedMovies(
                releaseYear = year.toInt(),
                voteGte = voteGte,
                voteLte = voteLte,
                runtimeGte = runtimeGte,
                runtimeLte = runtimeLte,
                pageNumber = currentPage
            )
        }

        // setting movie recycler view
        rv_movies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        movieAdapter = MovieAdapter(movies)
        rv_movies.adapter = movieAdapter
        movieAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(movies[position].id)
        }

        // load more movie event
        movieAdapter.setOnLoadMoreListener({
            if (totalPage <= currentPage)
                return@setOnLoadMoreListener

            when (searchMode) {
                SearchMode.NORMAL -> movieViewModel.getSearchedMovies(query, currentPage + 1)
                SearchMode.ADVANCE -> movieViewModel.getAdvancedSearchedMovies(
                    releaseYear = year.toInt(),
                    voteGte = voteGte,
                    voteLte = voteLte,
                    runtimeGte = runtimeGte,
                    runtimeLte = runtimeLte,
                    pageNumber = currentPage + 1
                )
            }
        }, rv_movies)


        movieViewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
            totalPage = movieList.totalPages
            currentPage = movieList.page
            if (currentPage == 1)
                movies.clear()
            movies.addAll(movieList.movieResults)
            movieAdapter.notifyDataSetChanged()
            movieAdapter.loadMoreComplete()
        })
    }
}