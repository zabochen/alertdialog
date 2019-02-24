package ua.ck.zabochen.alertdialog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import ua.ck.zabochen.alertdialog.R
import ua.ck.zabochen.androidx.dialog.ConfirmDialogFragment

class MainFragment : Fragment(), ConfirmDialogFragment.Listener {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonOpenDialog = view.findViewById<Button>(R.id.fragmentMain_button_openDialog)
        buttonOpenDialog.setOnClickListener {
            val confirmDialogFragment = ConfirmDialogFragment.newInstance(
                dialogTitle = "Fragment",
                dialogMessage = "Fragment Message",
                positiveButtonTitle = "YES",
                negativeButtonTitle = "NO"
            )
            confirmDialogFragment.setTargetFragment(this, 1)
            confirmDialogFragment.show(fragmentManager!!, "MainFragment")
        }
    }

    override fun positiveButtonClicked() {
        Toast.makeText(activity, "YES: Fragment", Toast.LENGTH_LONG)
            .show()
    }

    override fun negativeButtonClicked() {
        Toast.makeText(activity, "NO: Fragment", Toast.LENGTH_LONG)
            .show()
    }
}