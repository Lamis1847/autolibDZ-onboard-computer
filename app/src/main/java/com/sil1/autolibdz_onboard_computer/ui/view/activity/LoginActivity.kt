package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.CodePinRepository
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val pinEntry = findViewById<View>(R.id.txt_pin_entry) as PinEntryEditText
        pinEntry.setOnPinEnteredListener { str ->
            validerBoutton.setOnClickListener {
                var loginActivity = CodePinRepository.Companion
                loginActivity.codePin(this,str.toString())
            }
        }

    }
}