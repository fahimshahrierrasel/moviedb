package com.fahimshahrierrasel.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.helper.*
import com.fahimshahrierrasel.moviedb.ui.about.AboutFragment
import com.fahimshahrierrasel.moviedb.ui.about.AboutPresenter
import com.fahimshahrierrasel.moviedb.ui.views.*
import com.fahimshahrierrasel.moviedb.viewmodels.MovieViewModel
import com.mikepenz.materialdrawer.Drawer
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable


class MainActivity : AppCompatActivity() {
    private lateinit var myDrawer: Drawer
    val progressView by lazy { MovieDBProgressDialog(this) }
    private val genres by lazy { ArrayList<Genre>() }
    private val compositeDisposable = CompositeDisposable()

    private val movieViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Logger.addLogAdapter(AndroidLogAdapter())

        movieViewModel.getMovieGenres().observe(this, Observer { movieGenre ->
            genres.clear()
            genres.addAll(movieGenre.genres)
        })
        setDrawer()
        openMovieList()
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

    fun getGenreFromId(ids: List<Int>): String {

        val filteredGenre = ArrayList<Genre>()
        for (id in ids) {
            val genre = genres.find { it.id == id }
            if (genre != null)
                filteredGenre.add(genre)
        }

        return filteredGenre.joinToString { it.name }
    }

    private fun openMovieList(keyword: String = "popular") {
        val bundle = Bundle()
        bundle.putString(MOVIE_KEYWORD, keyword)

        val popularFragment = MovieListFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, popularFragment)
        }.commit()
    }

    private fun openGenreFragment() {
        val genreFragment = GenreFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, genreFragment)
        }.commit()
    }

    private fun openPersonFragment() {
        val personListFragment = ActorListFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, personListFragment)
        }.commit()
    }

    private fun openAboutFragment() {
        val aboutFragment = AboutFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, aboutFragment)
        }.commit()

        AboutPresenter(aboutFragment)
    }

    private fun openDiscoverFragment() {
        val discoverFragment = DiscoverMoviesFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, discoverFragment)
        }.commit()
    }

    fun openMovieDetails(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt(MOVIE_ID, movieId)
        val movieDetailsFragment = MovieDetailsFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, movieDetailsFragment)
            addToBackStack(null)
        }.commit()
    }

    fun openGenreMovies(genre: Genre) {
        val bundle = Bundle()
        bundle.putInt(GENRE_ID, genre.id)
        bundle.putString(GENRE_NAME, genre.name)
        val movieListFragment = MovieListFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, movieListFragment)
        }.commit()
    }

    fun openPersonDetails(personId: Int) {
        val bundle = Bundle()
        bundle.putInt(PERSON_ID, personId)
        val personDetailsFragment = ActorDetailsFragment.newInstance(bundle)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, personDetailsFragment)
            addToBackStack(null)
        }.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
