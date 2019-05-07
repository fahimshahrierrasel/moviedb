package com.fahimshahrierrasel.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import co.zsmb.materialdrawerkt.builders.DrawerBuilderKt
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.helper.GENRE_ID
import com.fahimshahrierrasel.moviedb.helper.GENRE_NAME
import com.fahimshahrierrasel.moviedb.helper.MOVIE_ID
import com.fahimshahrierrasel.moviedb.helper.MOVIE_KEYWORD
import com.fahimshahrierrasel.moviedb.ui.discover.DiscoverFragment
import com.fahimshahrierrasel.moviedb.ui.discover.DiscoverPresenter
import com.fahimshahrierrasel.moviedb.ui.genres.GenreFragment
import com.fahimshahrierrasel.moviedb.ui.genres.GenrePresenter
import com.fahimshahrierrasel.moviedb.ui.movie_details.MovieDetailsFragment
import com.fahimshahrierrasel.moviedb.ui.movie_details.MovieDetailsPresenter
import com.fahimshahrierrasel.moviedb.ui.movie_genre.MovieGenreFragment
import com.fahimshahrierrasel.moviedb.ui.movie_genre.MovieGenrePresenter
import com.fahimshahrierrasel.moviedb.ui.movie_list.MovieListFragment
import com.fahimshahrierrasel.moviedb.ui.movie_list.MovieListPresenter
import com.mikepenz.materialdrawer.Drawer
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class MainActivity : AppCompatActivity() {

    private lateinit var myDrawer: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.addLogAdapter(AndroidLogAdapter())

        myDrawer = drawer {
            primaryItem("Genres") {
                icon = R.drawable.ic_library_books_black_24dp
                onClick { _ ->
                    openGenreFragment()
                    false
                }
            }
            primaryItem("Popular") {
                icon = R.drawable.ic_stars_black_24dp
                onClick { _ ->
                    openMovieList()
                    false
                }
            }
            primaryItem("Discover") {
                icon = R.drawable.ic_movie_filter_black_24dp
                onClick { _ ->
                    openDiscoverFragment()
                    false
                }
            }
            primaryItem("Upcoming") {
                icon = R.drawable.ic_history_black_24dp
                onClick { _ ->
                    openMovieList("upcoming")
                    false
                }
            }
            primaryItem("Now Playing") {
                icon = R.drawable.ic_local_play_black_24dp
                onClick { _ ->
                    openMovieList("now_playing")
                    false
                }
            }
            primaryItem("Top Rated") {
                icon = R.drawable.ic_whatshot_black_24dp
                onClick { _ ->
                    openMovieList("top_rated")
                    false
                }
            }
            primaryItem("Person") {
                icon = R.drawable.ic_person_black_24dp
                onClick { _ ->

                    false
                }
            }
            divider {}
            primaryItem("About") {
                icon = R.drawable.ic_info_black_24dp
                onClick { _ ->

                    false
                }
            }
        }

//        openMovieList()
        openDiscoverFragment()
    }


    private fun openMovieList(keyword: String = "popular") {
        val bundle = Bundle()
        bundle.putString(MOVIE_KEYWORD, keyword)

        val popularFragment = MovieListFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, popularFragment)
        }.commit()

        MovieListPresenter(popularFragment)
    }

    private fun openGenreFragment() {
        val genreFragment = GenreFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, genreFragment)
        }.commit()

        GenrePresenter(genreFragment)
    }

    private fun openDiscoverFragment() {
        val discoverFragment = DiscoverFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, discoverFragment)
        }.commit()

        DiscoverPresenter(discoverFragment)
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

    fun openGenreMovies(genre: Genre) {
        val bundle = Bundle()
        bundle.putInt(GENRE_ID, genre.id)
        bundle.putString(GENRE_NAME, genre.name)
        val movieGenreFragment = MovieGenreFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, movieGenreFragment)
            addToBackStack(null)
        }.commit()
        MovieGenrePresenter(movieGenreFragment)
    }
}
