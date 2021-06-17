package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.utils.reservationG
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_state_on_drive.*
import kotlinx.android.synthetic.main.fragment_home_state_on_late.*

class HomeStateOnLateFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_state_on_late, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val depart = preferences?.getString("borneDName", "defaultValue")
        val arrivee = preferences?.getString("borneFName", "defaultValue")
        borneDepart3.text= depart
        borneArrivee3.text=arrivee

        //juste pour tester


    }
}