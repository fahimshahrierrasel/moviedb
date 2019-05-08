package com.fahimshahrierrasel.moviedb.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fahimshahrierrasel.moviedb.BuildConfig
import com.fahimshahrierrasel.moviedb.R
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment(), SplashContract.View {
    private lateinit var splashPresenter: SplashContract.Presenter

    companion object {
        fun newInstance(bundle: Bundle) = SplashFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_app_slogan.text = BuildConfig.VERSION_NAME
    }

    override fun showProgressView() {
        //
    }

    override fun hideProgressView() {
        //
    }

    override fun setPresenter(presenter: SplashContract.Presenter) {
        splashPresenter = presenter
    }
}