package com.fahimshahrierrasel.moviedb.helper

import android.content.Context
import android.util.TypedValue

object Tools {
    fun dpToPx(ctx: Context, dp: Int): Int {
        val r = ctx.resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }
}