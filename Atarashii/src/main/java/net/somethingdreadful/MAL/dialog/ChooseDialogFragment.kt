package net.somethingdreadful.MAL.dialog

import android.app.AlertDialog
import android.app.DialogFragment
import android.os.Bundle
import net.somethingdreadful.MAL.R

class ChooseDialogFragment : DialogFragment() {
    private var callback: onClickListener? = null

    override fun onCreateDialog(state: Bundle?): AlertDialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(arguments.getString("title"))
        builder.setMessage(arguments.getString("message"))
        builder.setNegativeButton(R.string.dialog_label_cancel) { _, _ -> dismiss() }
        builder.setPositiveButton(arguments.getString("positive")) { _, _ ->
            val pos = if (arguments.containsKey("pos")) arguments.getInt("pos") else -1
            callback!!.onPositiveButtonClicked(arguments.getInt("id"), pos)
            dismiss()
        }
        return builder.create()
    }

    fun setCallback(callback: onClickListener) {
        this.callback = callback
    }

    /**
     * The interface for callback
     */
    interface onClickListener {
        fun onPositiveButtonClicked(id: Int, pos: Int)
    }
}