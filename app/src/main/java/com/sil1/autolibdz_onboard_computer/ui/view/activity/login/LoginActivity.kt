package com.sil1.autolibdz_onboard_computer.ui.view.activity.login

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

    }
}