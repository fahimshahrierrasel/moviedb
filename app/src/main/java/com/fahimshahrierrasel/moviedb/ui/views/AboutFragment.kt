package com.fahimshahrierrasel.moviedb.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fahimshahrierrasel.moviedb.BuildConfig
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.helper.GITHUB_URL
import com.fahimshahrierrasel.moviedb.ui.MainActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : BaseFragment() {

    companion object {
        fun newInstance(bundle: Bundle) = AboutFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BuildConfig.VERSION_NAME.apply {
            tv_app_version.text = this
        }

        GITHUB_URL.apply {
            tv_github_url.text = this
        }

    }
}