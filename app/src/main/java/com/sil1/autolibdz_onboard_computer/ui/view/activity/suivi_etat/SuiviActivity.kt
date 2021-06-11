package com.sil1.autolibdz_onboard_computer.ui.view.activity.suivi_etat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sil1.autolibdz_onboard_computer.R
import kotlinx.android.synthetic.main.fragment_menu_bar.*

class SuiviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suivi)

        /*homeButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }*/
    }
}