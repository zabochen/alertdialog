package ua.ck.zabochen.androidx.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ua.ck.zabochen.androidx.internal.CONFIRM_DIALOG_MESSAGE_KEY
import ua.ck.zabochen.androidx.internal.CONFIRM_DIALOG_NEGATIVE_BUTTON_TITLE_KEY
import ua.ck.zabochen.androidx.internal.CONFIRM_DIALOG_POSITIVE_BUTTON_TITLE_KEY
import ua.ck.zabochen.androidx.internal.CONFIRM_DIALOG_TITLE_KEY

class ConfirmDialogActivity : DialogFragment() {

    interface Listener {
        fun positiveButtonClicked()
        fun negativeButtonClicked()
    }

    private lateinit var listener: Listener

    companion object {
        fun newInstance(
            dialogTitle: String = "",
            dialogMessage: String = "",
            positiveButtonTitle: String = "",
            negativeButtonTitle: String = ""
        ): ConfirmDialogActivity {
            val confirmDialog: ConfirmDialogActivity = ConfirmDialogActivity()

            // Put data
            val bundle: Bundle = Bundle()
            bundle.putString(CONFIRM_DIALOG_TITLE_KEY, dialogTitle)
            bundle.putString(CONFIRM_DIALOG_MESSAGE_KEY, dialogMessage)
            bundle.putString(CONFIRM_DIALOG_POSITIVE_BUTTON_TITLE_KEY, positiveButtonTitle)
            bundle.putString(CONFIRM_DIALOG_NEGATIVE_BUTTON_TITLE_KEY, negativeButtonTitle)
            confirmDialog.arguments = bundle

            return confirmDialog
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            this.listener = activity as Listener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement interface \"ConfirmDialogActivity.Listener\"")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!)
            .setTitle(arguments?.getString(CONFIRM_DIALOG_TITLE_KEY))
            .setMessage(arguments?.getString(CONFIRM_DIALOG_MESSAGE_KEY))
            .setPositiveButton(arguments?.getString(CONFIRM_DIALOG_POSITIVE_BUTTON_TITLE_KEY)) { _, _ ->
                listener.positiveButtonClicked()
            }
            .setNegativeButton(arguments?.getString(CONFIRM_DIALOG_NEGATIVE_BUTTON_TITLE_KEY)) { _, _ ->
                listener.negativeButtonClicked()
            }
            .create()
    }
}