package com.fahimshahrierrasel.moviedb.ui.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.CreditCast
import com.fahimshahrierrasel.moviedb.helper.castProfilePrefix

class CastAdapter(creditCasts: List<CreditCast>) :
    BaseQuickAdapter<CreditCast, BaseViewHolder>(R.layout.item_cast, creditCasts) {
    override fun convert(helper: BaseViewHolder, item: CreditCast) {
        helper.setText(R.id.tv_cast_name, item.name)

        val poster: ImageView = helper.getView(R.id.iv_cast_photo)
        Glide.with(mContext).load("$castProfilePrefix${item.profilePath}").into(poster)
    }
}