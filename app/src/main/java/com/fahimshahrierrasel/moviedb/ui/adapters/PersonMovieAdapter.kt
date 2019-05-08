package com.fahimshahrierrasel.moviedb.ui.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Cast
import com.fahimshahrierrasel.moviedb.data.model.CreditCast
import com.fahimshahrierrasel.moviedb.helper.castProfilePrefix
import com.fahimshahrierrasel.moviedb.helper.posterPrefix

class PersonMovieAdapter(casts: List<Cast>) :
    BaseQuickAdapter<Cast, BaseViewHolder>(R.layout.item_person_movie, casts) {
    override fun convert(helper: BaseViewHolder, item: Cast) {
        helper.setText(R.id.tv_movie_name, item.title)

        val poster: ImageView = helper.getView(R.id.iv_movie_poster)
        Glide.with(mContext).load("$posterPrefix${item.posterPath}").into(poster)
    }
}