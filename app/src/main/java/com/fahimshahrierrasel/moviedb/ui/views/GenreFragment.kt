package com.fahimshahrierrasel.moviedb.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.helper.SpacingItemDecoration
import com.fahimshahrierrasel.moviedb.helper.Tools
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.GenreAdapter
import com.fahimshahrierrasel.moviedb.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_genres.*

class GenreFragment : BaseFragment() {
    private lateinit var genreAdapter: GenreAdapter
    private val genres = ArrayList<Genre>()

    companion object {
        fun newInstance(bundle: Bundle) = GenreFragment().apply {
            arguments = bundle
        }
    }

    private val movieViewModel by lazy {
        ViewModelProvider(rootActivity, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_genres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = "Movie Genres"

        // Setting genre recycler view
        rv_genres.layoutManager = GridLayoutManager(rootActivity, 2)
        rv_genres.addItemDecoration(
            SpacingItemDecoration(
                2,
                Tools.dpToPx(rootActivity, 2),
                true
            )
        )
        rv_genres.setHasFixedSize(true)
        rv_genres.isNestedScrollingEnabled = false
        genreAdapter = GenreAdapter(genres)
        rv_genres.adapter = genreAdapter

        movieViewModel.getMovieGenres().observe(viewLifecycleOwner, Observer { movieGenre ->
            populateGenreRecyclerView(movieGenre.genres)
        })

        // Genre click event open movie list with same genre
        genreAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openGenreMovies(genres[position])
        }
    }


    private fun populateGenreRecyclerView(genres: List<Genre>) {
        this.genres.clear()
        this.genres.addAll(genres)
        genreAdapter.notifyDataSetChanged()
    }
}