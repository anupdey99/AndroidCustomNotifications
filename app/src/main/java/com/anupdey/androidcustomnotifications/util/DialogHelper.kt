package com.anupdey.androidcustomnotifications.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogHelper {

    fun showAlertDialog(
        context: Context,
        data: DialogData,
        onPositiveBtnClickListener: (() -> Unit)? = null,
        onNegativeBtnClickListener: (() -> Unit)? = null
    ) {
        val titleText = if (data.titleId != 0) {
            context.getString(data.titleId)
        } else {
            data.title
        }
        val messageText = if (data.messageId != 0) {
            context.getString(data.messageId)
        } else {
            data.message
        }
        val negativeText = if (data.negativeBtnTextId != 0) {
            context.getString(data.negativeBtnTextId)
        } else {
            data.negativeBtnText ?: ""
        }
        val positiveText = if (data.positiveBtnTextId != 0) {
            context.getString(data.positiveBtnTextId)
        } else {
            data.positiveBtnText ?: ""
        }
        val iconDrawable = if (data.icon != 0) ContextCompat.getDrawable(context, data.icon) else null
        MaterialAlertDialogBuilder(context)
            .setTitle(titleText)
            .setIcon(iconDrawable)
            .setMessage(messageText)
            .setNegativeButton(negativeText) { _, _ ->
                onNegativeBtnClickListener?.invoke()
            }
            .setPositiveButton(positiveText) { _, _ ->
                onPositiveBtnClickListener?.invoke()
            }
            .setCancelable(data.isCancelable)
            .show()
    }

}

data class DialogData(
    @StringRes
    val titleId: Int = 0,
    val title: String? = null,

    @StringRes
    val messageId: Int = 0,
    val message: String? = null,

    @DrawableRes
    val icon: Int = 0,

    @StringRes
    val positiveBtnTextId: Int = 0,
    val positiveBtnText: String? = null,

    @StringRes
    val negativeBtnTextId: Int = 0,
    val negativeBtnText: String? = null,
    val isCancelable: Boolean = false
)