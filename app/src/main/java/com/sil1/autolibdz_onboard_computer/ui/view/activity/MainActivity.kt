package com.sil1.autolibdz_onboard_computer.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.model.InfotrajetModel
import com.sil1.autolibdz_onboard_computer.utils.locName
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vm= ViewModelProvider(this).get(InfotrajetModel::class.java)

        locataireName.text= "Bonjour "+vm.locataire.nom


    }
}