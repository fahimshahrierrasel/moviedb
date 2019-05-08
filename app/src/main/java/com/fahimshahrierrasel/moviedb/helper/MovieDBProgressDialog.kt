package com.fahimshahrierrasel.moviedb.helper

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.fahimshahrierrasel.moviedb.R


class MovieDBProgressDialog(context: Context) : AlertDialog.Builder(context, R.style.ProgressDialogStyle) {
    private val dialog: AlertDialog

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.progess_view, null)

        setView(view)
        setCancelable(false)
        dialog = create()
    }

    fun hide() {
        dialog.hide()
    }

    override fun show(): AlertDialog {
        dialog.show()
        return dialog
    }

    val isShowing get() = dialog.isShowing

}