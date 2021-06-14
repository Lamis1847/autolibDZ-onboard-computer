package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.model.Reservation
import com.sil1.autolibdz_onboard_computer.data.repositories.trajetRepository
import com.sil1.autolibdz_onboard_computer.utils.reservationG
import com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne.ReportPanneActivity
import com.sil1.autolibdz_onboard_computer.ui.view.activity.suivi_etat.SuiviActivity
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_menu_bar.*
import kotlinx.android.synthetic.main.fragment_suivi_one.*
import java.time.LocalDateTime

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

        val currentDateTime = LocalDateTime.now()
        val reservation: Reservation


        conduireButton1.setOnClickListener {
            var startTrajetActivity = trajetRepository.Companion
            reservationG.etat= "Active"
            startTrajetActivity.startTrajet(
                view, requireContext().applicationContext, reservationG, currentDateTime.toString()
            )
            println(reservationG)
            // view?.findNavController()?.navigate(R.id.action_homeFragment_to_homeStateOnDriveFragment)
        }



        naviguerButton1.setOnClickListener {

            val intent = Intent(context, NavigationActivity::class.java)
            startActivity(intent)

        }

        /*fragment.appButton.setOnClickListener {
            val myIntent = Intent(context, SuiviActivity::class.java)
            startActivity(myIntent)
        }

        fragment.triangleAlertButton.setOnClickListener {
            val myIntent = Intent(context, ReportPanneActivity::class.java)
            startActivity(myIntent)
        }*/
    }

}