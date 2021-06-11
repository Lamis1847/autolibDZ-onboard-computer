package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.view.Window
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.sil1.autolibdz_onboard_computer.R
import kotlinx.android.synthetic.main.fragment_popup_panne.*
import kotlinx.android.synthetic.main.fragment_popup_panne.view.*

class CustomAlert {

    fun showDialog(activity: Activity?) {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.fragment_first_pop_up)

        val dialogButton: Button = dialog.findViewById<View>(R.id.button2) as Button
        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun showDialog2(activity: Activity?) {

        val dialog = Dialog(activity!!)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.fragment_popup_panne)

        val dialogButton: Button = dialog.findViewById<View>(R.id.button3) as Button
        dialogButton.setOnClickListener {
            /*Toast.makeText(
                activity.applicationContext,
                s,
                Toast.LENGTH_SHORT
            )
                .show()*/
            dialog.dismiss()
        }
        dialog.show()
    }
}