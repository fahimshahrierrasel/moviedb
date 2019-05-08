package com.fahimshahrierrasel.moviedb.ui.person_details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.*
import com.fahimshahrierrasel.moviedb.helper.*
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.PersonMovieAdapter
import kotlinx.android.synthetic.main.content_person.*
import kotlinx.android.synthetic.main.fragment_movie_details.toolbar
import kotlinx.android.synthetic.main.fragment_person_details.*

class PersonDetailsFragment : Fragment(), PersonDetailsContract.View {
    private lateinit var personDetailsPresenter: PersonDetailsContract.Presenter
    private lateinit var movieAdapter: PersonMovieAdapter
    private val casts = ArrayList<Cast>()
    private lateinit var rootActivity: MainActivity

    companion object {
        fun newInstance(bundle: Bundle) = PersonDetailsFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Preparing movie recycler view
        rv_person_movies.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        movieAdapter = PersonMovieAdapter(casts)
        rv_person_movies.adapter = movieAdapter

        // Movie item click listener
        movieAdapter.setOnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(casts[position].id)
        }
    }

    override fun showProgressView() {
        rootActivity.progressView.show()
    }

    override fun hideProgressView() {
        rootActivity.progressView.hide()
    }

    override fun findPersonId() {
        val personId = arguments?.getInt(PERSON_ID)
        personDetailsPresenter.getPersonDetails(personId!!)
    }

    override fun populatePersonDetails(person: Person) {
        Glide.with(context!!)
            .load("$castProfilePrefix${person.profilePath}")
            .into(iv_person_image)


        // setting actor name as toolbar title
        toolbar.title = person.name
        toolbar.setTitleTextColor(Color.WHITE)

        tv_person_biography.text = person.biography
        tv_known_name.text = person.alsoKnownAs.joinToString()
        tv_birth_date.text = person.birthday
        tv_birth_place.text = person.placeOfBirth
    }

    override fun populateMovieCredits(personCreditResponse: PersonCreditResponse) {
        if (personCreditResponse.cast.size > 10)
            casts.addAll(personCreditResponse.cast.subList(0, 10))
        else
            casts.addAll(personCreditResponse.cast)
        movieAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        personDetailsPresenter.start()
    }

    override fun setPresenter(presenter: PersonDetailsContract.Presenter) {
        personDetailsPresenter = presenter
    }
}