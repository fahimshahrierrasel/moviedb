package com.fahimshahrierrasel.moviedb.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fahimshahrierrasel.moviedb.R

class PopularFragment : Fragment(), PopularContract.View {
    private lateinit var popularPresenter: PopularContract.Presenter

    companion object {
        fun newInstance(bundle: Bundle) = PopularFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setPresenter(presenter: PopularContract.Presenter) {
        popularPresenter = presenter
    }

}