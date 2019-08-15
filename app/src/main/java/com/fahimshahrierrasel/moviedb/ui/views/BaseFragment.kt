package com.fahimshahrierrasel.moviedb.ui.views

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.ui.MainActivity

abstract class BaseFragment : Fragment() {
    protected val rootActivity: MainActivity by lazy {
        activity as MainActivity
    }

    protected abstract val recyclerView: RecyclerView

    protected val loadingProgressView: View by lazy {
        layoutInflater.inflate(R.layout.progess_view, recyclerView.parent as ViewGroup, false)
    }
}