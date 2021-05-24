package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.sil1.autolibdz_onboard_computer.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        validerBoutton.setOnClickListener {
            //number is (max=4) already implemented
            val pinEntry = findViewById<View>(R.id.txt_pin_entry) as PinEntryEditText

            if (pinEntry != null) {
                pinEntry.setOnPinEnteredListener { str ->
                    println(str.toString())
                }
            }
        }
    }
}