package com.fahimshahrierrasel.moviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.zsmb.materialdrawerkt.builders.drawer
import co.zsmb.materialdrawerkt.draweritems.badgeable.primaryItem
import co.zsmb.materialdrawerkt.draweritems.badgeable.secondaryItem
import co.zsmb.materialdrawerkt.draweritems.divider
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.ui.popular.PopularFragment
import com.fahimshahrierrasel.moviedb.ui.popular.PopularPresenter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer {
            primaryItem("Popular") {}
            primaryItem("Discover") {}
            primaryItem("Upcoming") {}
            primaryItem("Now Playing") {}
            primaryItem("Top Rated") {}
            primaryItem("Person") {}
            divider {}
            primaryItem("About") {}
        }

        val popularFragment = PopularFragment.newInstance(Bundle())
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment_placeholder, popularFragment)
        }.commit()

        PopularPresenter(popularFragment)
    }
}
