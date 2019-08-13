package com.fahimshahrierrasel.moviedb.ui.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.PersonResult
import com.fahimshahrierrasel.moviedb.helper.castProfilePrefix

class PersonAdapter(personResults: List<PersonResult>) :
    BaseQuickAdapter<PersonResult, BaseViewHolder>(R.layout.item_actor, personResults) {
    override fun convert(helper: BaseViewHolder, item: PersonResult) {
        helper.setText(R.id.tv_actor_name, item.name)

        val poster: ImageView = helper.getView(R.id.iv_actor_photo)
        Glide.with(mContext).load("$castProfilePrefix${item.profilePath}").into(poster)
    }
}