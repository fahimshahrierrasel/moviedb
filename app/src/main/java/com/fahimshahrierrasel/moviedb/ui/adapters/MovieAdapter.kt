package com.fahimshahrierrasel.moviedb.ui.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.helper.posterPrefix

class MovieAdapter(movieResults: List<MovieResult>) :
    BaseQuickAdapter<MovieResult, BaseViewHolder>(R.layout.item_movie, movieResults) {
    override fun convert(helper: BaseViewHolder, item: MovieResult) {
        helper.setText(R.id.tv_movie_title, item.title)
            .setText(R.id.tv_release_date, item.releaseDate)
            .setText(R.id.tv_rating, item.voteAverage.toString())

        val poster: ImageView = helper.getView(R.id.iv_movie_poster)
        Glide.with(mContext).load("$posterPrefix${item.posterPath}").into(poster)
    }
}