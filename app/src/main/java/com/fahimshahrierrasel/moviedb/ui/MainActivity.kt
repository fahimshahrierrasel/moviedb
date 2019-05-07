package com.fahimshahrierrasel.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Movie
import com.fahimshahrierrasel.moviedb.helper.MOVIE_ID
import com.fahimshahrierrasel.moviedb.ui.movie_details.MovieDetailsFragment
import com.fahimshahrierrasel.moviedb.ui.movie_details.MovieDetailsPresenter
import com.fahimshahrierrasel.moviedb.ui.popular.PopularFragment
import com.fahimshahrierrasel.moviedb.ui.popular.PopularPresenter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.addLogAdapter(AndroidLogAdapter())

        drawer {
            primaryItem("Popular") {}
            primaryItem("Discover") {}
            primaryItem("Upcoming") {}
            primaryItem("Now Playing") {}
            primaryItem("Top Rated") {}
            primaryItem("Person") {}
            divider {}
            primaryItem("About") {}
        }

        val popularFragment = PopularFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, popularFragment)
        }.commit()

        PopularPresenter(popularFragment)
    }

    fun openMovieDetails(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID, movieId)
        val movieDetailsFragment = MovieDetailsFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, movieDetailsFragment)
            addToBackStack(null)
        }.commit()
        MovieDetailsPresenter(movieDetailsFragment)
    }
}
