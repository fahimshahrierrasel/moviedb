package com.fahimshahrierrasel.moviedb.ui.views

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Cast
import com.fahimshahrierrasel.moviedb.data.model.Person
import com.fahimshahrierrasel.moviedb.data.model.PersonCreditResponse
import com.fahimshahrierrasel.moviedb.helper.PERSON_ID
import com.fahimshahrierrasel.moviedb.helper.castProfilePrefix
import com.fahimshahrierrasel.moviedb.ui.adapters.PersonMovieAdapter
import com.fahimshahrierrasel.moviedb.viewmodels.ActorViewModel
import kotlinx.android.synthetic.main.content_person.*
import kotlinx.android.synthetic.main.fragment_person_details.*

class ActorDetailsFragment : BaseFragment() {
    private lateinit var movieAdapter: PersonMovieAdapter
    private val casts = ArrayList<Cast>()

    private val actorViewModel by lazy {
        ViewModelProvider(rootActivity, ViewModelProvider.NewInstanceFactory()).get(ActorViewModel::class.java)
    }

    companion object {
        fun newInstance(bundle: Bundle) = ActorDetailsFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personId = arguments?.getInt(PERSON_ID)

        // Preparing movie recycler view
        rv_person_movies.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        movieAdapter = PersonMovieAdapter(casts)
        rv_person_movies.adapter = movieAdapter

        actorViewModel.getActorDetails(personId!!).observe(viewLifecycleOwner, Observer { person ->
            populateActorDetails(person)
        })

        actorViewModel.getActorMovies(personId).observe(viewLifecycleOwner, Observer { creditResponse ->
            populateMovieCredits(creditResponse)
        })

        // Movie item click listener
        movieAdapter.setOnItemClickListener { _, _, position ->
            rootActivity.openMovieDetails(casts[position].id)
        }
    }


    private fun populateActorDetails(person: Person) {
        Glide.with(rootActivity)
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

    private fun populateMovieCredits(personCreditResponse: PersonCreditResponse) {
        if (personCreditResponse.cast.size > 10)
            casts.addAll(personCreditResponse.cast.subList(0, 10))
        else
            casts.addAll(personCreditResponse.cast)
        movieAdapter.notifyDataSetChanged()
    }

}