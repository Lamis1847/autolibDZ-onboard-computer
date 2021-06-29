package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.model.Reservation
import com.sil1.autolibdz_onboard_computer.data.repositories.trajetRepository
import com.sil1.autolibdz_onboard_computer.utils.reservationG
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_menu_bar.*
import kotlinx.android.synthetic.main.fragment_menu_bar.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val depart = preferences?.getString("borneDName", "defaultValue")
        val arrivee = preferences?.getString("borneFName", "defaultValue")

        borneDepart1.text= depart
        borneArrivee1.text=arrivee
        tarifTotal1.text = reservationG.prixEstime.toString()
        tempEstime1.text = reservationG.tempsEstime.toString()+" minutes"




        conduireButton1.setOnClickListener {
            /*     currentDT =simpleDateFormat.format(Date())
                 var startTrajetActivity = trajetRepository.Companion
                 reservationG.etat= "Active"
                 startTrajetActivity.startTrajet(
                     view, requireContext().applicationContext, reservationG, currentDT
                 )*/
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_homeStateOnDriveFragment)
        }



      /*  naviguerButton1.setOnClickListener {
            val intent = Intent(context, MapActivity::class.java)
            startActivity(intent)
        }*/

    }

}