package com.matias.moviesapp.util

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.matias.moviesapp.R
import com.matias.moviesapp.ui.entity.AlertData

object DialogUtil {

    fun showDialog(context: Context, alertData: AlertData) {
        val positiveBtnText = alertData.positiveAction.first.ifEmpty {
            context.getString(R.string.dialog_btn_accept)
        }

        val dialog = MaterialAlertDialogBuilder(context)
            .setTitle(alertData.title)
            .setMessage(alertData.message)
            .setPositiveButton(positiveBtnText) { _, _ -> alertData.positiveAction.second() }
            .setOnDismissListener { alertData.dismissAction() }

        if (alertData.negativeAction.first.isNotEmpty()) {
            dialog.setNegativeButton(alertData.negativeAction.first) { _, _ ->
                alertData.negativeAction.second()
            }
        }

        dialog.show()
    }
}
