package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.UpdateVehiculeRepository
import com.sil1.autolibdz_onboard_computer.data.repositories.quitterRepository
import com.sil1.autolibdz_onboard_computer.ui.view.activity.login.LoginActivity
import com.sil1.autolibdz_onboard_computer.utils.reservationG
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_state_on_drive.*
import kotlinx.android.synthetic.main.fragment_home_state_on_late.*


class HomeStateOnDriveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_state_on_drive, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       var preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        var depart = preferences?.getString("borneDName", "defaultValue")
        var arrivee = preferences?.getString("borneFName", "defaultValue")
          preferences?.getString("dateBeginTrajet", "2021-06-28 15:06:57")

        borneDepart2.text= depart
        borneArrivee2.text=arrivee
        tarifTotal2.text = reservationG.prixEstime.toString()
        tempEstime2.text = reservationG.tempsEstime.toString()+" minutes"

        //juste pour tester
        naviguerButton2.setOnClickListener {
            val intent = Intent(context,MapActivity::class.java)
            startActivity(intent)
        }
        quitterVehiculeButton2.setOnClickListener {

            var quitter = quitterRepository.Companion
            quitter.quite(requireContext(), reservationG.idReservation)

            val myIntent = Intent(context, LoginActivity::class.java)
            context?.startActivity(myIntent)
            (context as Activity).finish()
        }

    }
}