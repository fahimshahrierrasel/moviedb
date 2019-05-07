package com.fahimshahrierrasel.moviedb.ui.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.moviedb.R
import com.fahimshahrierrasel.moviedb.data.api.ApiUtils
import com.fahimshahrierrasel.moviedb.data.model.Genre
import com.fahimshahrierrasel.moviedb.data.model.MovieList
import com.fahimshahrierrasel.moviedb.data.model.MovieResult
import com.fahimshahrierrasel.moviedb.helper.apiKey
import com.fahimshahrierrasel.moviedb.helper.posterPrefix
import com.orhanobut.logger.Logger
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class GenreAdapter(genres: List<Genre>) :
    BaseQuickAdapter<Genre, BaseViewHolder>(R.layout.item_genre, genres) {
    override fun convert(helper: BaseViewHolder, item: Genre) {
        helper.setText(R.id.tv_genre_name, item.name)

        val poster: ImageView = helper.getView(R.id.iv_genre_photo)
        ApiUtils.movieDBService.requestForGenreMovies(item.id, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieList> {
                override fun onSuccess(t: MovieList) {
                    Glide.with(mContext)
                        .load("$posterPrefix${t.movieResults[0].posterPath}")
                        .into(poster)
                }

                override fun onSubscribe(d: Disposable) {
                    //
                }

                override fun onError(e: Throwable) {
                    Logger.e(e.localizedMessage)
                }

            })
    }
}