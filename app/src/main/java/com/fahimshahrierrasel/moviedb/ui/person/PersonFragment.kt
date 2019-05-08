package com.fahimshahrierrasel.moviedb.ui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.PersonResult
import com.fahimshahrierrasel.moviedb.helper.SpacingItemDecoration
import com.fahimshahrierrasel.moviedb.helper.Tools
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.PersonAdapter
import kotlinx.android.synthetic.main.fragment_genres.*

class PersonFragment : Fragment(), PersonContract.View {
    private lateinit var personPresenter: PersonContract.Presenter
    private lateinit var personAdapter: PersonAdapter
    private val personResults = ArrayList<PersonResult>()
    private lateinit var rootActivity: MainActivity

    companion object {
        fun newInstance(bundle: Bundle) = PersonFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootActivity = activity as MainActivity
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

        // Actor item click listener. Open actor details fragment
        personAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            rootActivity.openPersonDetails(personResults[position].id)
        }

        // Load more actor event. Triggered when scrolling to the last item
        personAdapter.setOnLoadMoreListener({
            personPresenter.loadNextPage()
        }, rv_genres)
    }

    override fun stopLoadMore() {
        personAdapter.loadMoreComplete()
    }

    override fun showProgressView() {
        rootActivity.progressView.show()
    }

    override fun hideProgressView() {
        rootActivity.progressView.hide()
    }

    override fun populatePersonRecyclerView(personResults: List<PersonResult>) {
        this.personResults.addAll(personResults)
        personAdapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        personPresenter.start()
    }

    override fun setPresenter(presenter: PersonContract.Presenter) {
        personPresenter = presenter
    }
}