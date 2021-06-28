package com.sil1.autolibdz_onboard_computer.ui.view.activity.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.CodePinRepository
import kotlinx.android.synthetic.main.fragment_code_pin.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_code_pin)

        val pinEntry = findViewById<View>(R.id.txt_pin_entry) as PinEntryEditText
        pinEntry.setOnPinEnteredListener { str ->
            validerBoutton.setOnClickListener {
                val loginActivity = CodePinRepository.Companion
                loginActivity.codePin(this,str.toString())
            }
        }

    }
}