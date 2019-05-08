package com.fahimshahrierrasel.moviedb.ui

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.helper.*
import com.fahimshahrierrasel.moviedb.ui.about.AboutFragment
import com.fahimshahrierrasel.moviedb.ui.about.AboutPresenter
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
import com.fahimshahrierrasel.moviedb.ui.person.PersonFragment
import com.fahimshahrierrasel.moviedb.ui.person.PersonPresenter
import com.fahimshahrierrasel.moviedb.ui.person_details.PersonDetailsFragment
import com.fahimshahrierrasel.moviedb.ui.person_details.PersonDetailsPresenter
import com.fahimshahrierrasel.moviedb.ui.splash.SplashFragment
import com.fahimshahrierrasel.moviedb.ui.splash.SplashPresenter
import com.mikepenz.materialdrawer.Drawer
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class MainActivity : AppCompatActivity() {
    private lateinit var myDrawer: Drawer
    val progressView by lazy { MovieDBProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.addLogAdapter(AndroidLogAdapter())

        // Initializing splash fragment
        val splashFragment = SplashFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, splashFragment)
        }.commit()
        SplashPresenter(splashFragment)

        Handler().postDelayed({
            openMovieList()
            setDrawer()
        }, 3000)
    }

    private fun setDrawer() {
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
                    openPersonFragment()
                    false
                }
            }
            divider {}
            primaryItem("About") {
                icon = R.drawable.ic_info_black_24dp
                onClick { _ ->
                    openAboutFragment()
                    false
                }
            }
        }
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

    private fun openPersonFragment() {
        val personFragment = PersonFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, personFragment)
        }.commit()

        PersonPresenter(personFragment)
    }

    private fun openAboutFragment() {
        val aboutFragment = AboutFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, aboutFragment)
        }.commit()

        AboutPresenter(aboutFragment)
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

    fun openPersonDetails(personId: Int) {
        val bundle = Bundle()
        bundle.putInt(PERSON_ID, personId)
        val personDetailsFragment = PersonDetailsFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, personDetailsFragment)
            addToBackStack(null)
        }.commit()
        PersonDetailsPresenter(personDetailsFragment)
    }
}
