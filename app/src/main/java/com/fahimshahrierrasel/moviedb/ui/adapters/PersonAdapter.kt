package com.fahimshahrierrasel.moviedb.ui.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Cast
import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.data.model.PersonResult
import com.fahimshahrierrasel.moviedb.helper.castProfilePrefix
import com.fahimshahrierrasel.moviedb.helper.posterPrefix

class PersonAdapter(personResults: List<PersonResult>) :
    BaseQuickAdapter<PersonResult, BaseViewHolder>(R.layout.item_genre, personResults) {
    override fun convert(helper: BaseViewHolder, item: PersonResult) {
        helper.setText(R.id.tv_genre_name, item.name)

        val poster: ImageView = helper.getView(R.id.iv_genre_photo)
        Glide.with(mContext).load("$castProfilePrefix${item.profilePath}").into(poster)
    }
}