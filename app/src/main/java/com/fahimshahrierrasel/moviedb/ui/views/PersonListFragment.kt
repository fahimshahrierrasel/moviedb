package com.fahimshahrierrasel.moviedb.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.PersonResult
import com.fahimshahrierrasel.moviedb.helper.SpacingItemDecoration
import com.fahimshahrierrasel.moviedb.helper.Tools
import com.fahimshahrierrasel.moviedb.ui.adapters.PersonAdapter
import com.fahimshahrierrasel.moviedb.viewmodels.ActorViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_genres.*

class PersonListFragment :BaseFragment() {
    private lateinit var personAdapter: PersonAdapter
    private val personResults by lazy {
        ArrayList<PersonResult>()
    }
    private var currentPage = 1

    private val personViewModel by lazy {
        ViewModelProvider(rootActivity, ViewModelProvider.NewInstanceFactory()).get(ActorViewModel::class.java)
    }

    companion object {
        fun newInstance(bundle: Bundle) = PersonListFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_genres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = "Popular Persons"

        // Setting actor recycler view.
        // This fragment is reused from genre so rv_genres is acting as actor recycler view
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
        personAdapter = PersonAdapter(personResults)
        rv_genres.adapter = personAdapter

        personViewModel.getActorResponse()
        personViewModel.personResponse.observe(viewLifecycleOwner, Observer { personResponse ->
            Logger.d("Current Page: ${personResponse.page}")
            currentPage = personResponse.page
            personResults.addAll(personResponse.results)
            personAdapter.notifyDataSetChanged()
        })

        // Actor item click listener. Open actor details fragment
        personAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openPersonDetails(personResults[position].id)
        }


        // Load more actor event. Triggered when scrolling to the last item
        personAdapter.setOnLoadMoreListener({
            Logger.d("Change Page From $currentPage to ${currentPage + 1}")
            personViewModel.getActorResponse(currentPage + 1)
        }, rv_genres)
    }
}