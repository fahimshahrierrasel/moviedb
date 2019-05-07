package com.fahimshahrierrasel.moviedb.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.helper.SpacingItemDecoration
import com.fahimshahrierrasel.moviedb.helper.Tools
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.fahimshahrierrasel.moviedb.ui.adapters.GenreAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_genres.*
import kotlinx.android.synthetic.main.fragment_genres.toolbar

class DiscoverFragment : Fragment(), DiscoverContract.View {
    private lateinit var genrePresenter: DiscoverContract.Presenter
    private lateinit var genreAdapter: GenreAdapter
    private val genres = ArrayList<Genre>()
    private lateinit var rootActivity: MainActivity
    private var isAdvanceSearchShown = false

    companion object {
        fun newInstance(bundle: Bundle) = DiscoverFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.title = "Discover Movies"

        iv_show_hide.setOnClickListener {
            isAdvanceSearchShown = !isAdvanceSearchShown
            if (isAdvanceSearchShown)
                ll_advance_search.visibility = View.VISIBLE
            else
                ll_advance_search.visibility = View.GONE
        }

        rs_rating.getThumb(0).value = 5
        rs_rating.getThumb(1).value = 10

        rs_duration.getThumb(0).value = 70
        rs_duration.getThumb(1).value = 200

        tv_rating.text = "Rating: 5 - 10"
        tv_duration.text = "Duration: 70 - 200"

        rs_rating.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            val min = multiSlider.getThumb(0).value
            val max = multiSlider.getThumb(1).value

            tv_rating.text = "Rating: $min - $max"
        }

        rs_duration.setOnThumbValueChangeListener { multiSlider, thumb, thumbIndex, value ->
            val min = multiSlider.getThumb(0).value
            val max = multiSlider.getThumb(1).value

            tv_duration.text = "Duration: $min - $max"
        }

        val years = ArrayList<String>()
        for (year in 2019 downTo 1901 step 1)
            years.add(year.toString())

        val yearAdapter = ArrayAdapter<String>(rootActivity, android.R.layout.simple_spinner_dropdown_item, years)
        spinner_year.adapter = yearAdapter


//        rv_genres.layoutManager = GridLayoutManager(rootActivity, 2)
//        rv_genres.addItemDecoration(
//            SpacingItemDecoration(
//                2,
//                Tools.dpToPx(rootActivity, 2),
//                true
//            )
//        )
//        rv_genres.setHasFixedSize(true)
//        rv_genres.isNestedScrollingEnabled = false
//        genreAdapter = GenreAdapter(genres)
//        rv_genres.adapter = genreAdapter
//        genreAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
//            rootActivity.openGenreMovies(genres[position])
//        }
    }


    override fun onStart() {
        super.onStart()
        genrePresenter.start()
    }

    override fun setPresenter(presenter: DiscoverContract.Presenter) {
        genrePresenter = presenter
    }
}