package com.fauzimaulana.jsmapp.core.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.fauzimaulana.jsmapp.R

object Utils {
    fun showAlertNoInternet(context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        with(alertDialogBuilder) {
            setTitle(context.resources.getString(R.string.no_internet_title))
            setMessage(context.resources.getString(R.string.no_internet_message))
            setCancelable(false)
            setPositiveButton(context.getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}