package com.fahimshahrierrasel.moviedb.ui.adapters

import android.graphics.Color
import androidx.cardview.widget.CardView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.helper.cardColors
import java.security.SecureRandom

class GenreAdapter(genres: List<Genre>) :
    BaseQuickAdapter<Genre, BaseViewHolder>(R.layout.item_genre, genres) {
    override fun convert(helper: BaseViewHolder, item: Genre) {
        helper.setText(R.id.tv_genre_name, item.name)
        val genreCardView: CardView = helper.getView(R.id.genre_card)
        genreCardView.setCardBackgroundColor(Color.parseColor(cardColors.shuffled().first()))
    }
}