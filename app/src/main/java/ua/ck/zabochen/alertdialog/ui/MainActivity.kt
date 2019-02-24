package ua.ck.zabochen.alertdialog.ui

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ua.ck.zabochen.alertdialog.R
import ua.ck.zabochen.androidx.dialog.ConfirmDialogActivity

class MainActivity : AppCompatActivity(), ConfirmDialogActivity.Listener {

    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUi()
        if (savedInstanceState == null) {
            setFragment(frameLayout, MainFragment.newInstance())
        }
    }

    override fun positiveButtonClicked() {
        Toast.makeText(applicationContext, "YES: Activity", Toast.LENGTH_LONG)
            .show()
    }

    override fun negativeButtonClicked() {
        Toast.makeText(applicationContext, "NO: Activity", Toast.LENGTH_LONG)
            .show()
    }

    private fun setUi() {
        // Layout
        setContentView(R.layout.activity_main)

        // FrameLayout
        this.frameLayout = findViewById(R.id.activityMain_frameLayout_fragmentHolder)

        // Button: Open Dialog
        val buttonOpenDialog = findViewById<Button>(R.id.activityMain_button_openDialog)
        buttonOpenDialog.setOnClickListener {

            val confirmDialogActivity: ConfirmDialogActivity = ConfirmDialogActivity.newInstance(
                dialogTitle = "Activity",
                dialogMessage = "Activity Message",
                positiveButtonTitle = "YES",
                negativeButtonTitle = "NO"
            )
            confirmDialogActivity.show(supportFragmentManager, "ActivityDialog")
        }
    }

    private fun setFragment(frameLayout: FrameLayout, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(frameLayout.id, fragment)
            .commit()
    }
}
