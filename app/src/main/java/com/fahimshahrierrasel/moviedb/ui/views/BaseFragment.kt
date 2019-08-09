package com.fahimshahrierrasel.moviedb.ui.views

import androidx.fragment.app.Fragment
import com.fahimshahrierrasel.moviedb.ui.MainActivity

abstract class BaseFragment: Fragment() {
    protected val rootActivity: MainActivity by lazy {
        activity as MainActivity
    }
}