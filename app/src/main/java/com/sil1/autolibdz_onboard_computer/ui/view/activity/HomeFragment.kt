package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val depart = preferences?.getString("borneDName", "defaultValue")
        val arrivee = preferences?.getString("borneFName", "defaultValue")
        borneDepart1.text= depart
        borneArrivee1.text=arrivee
        conduireButton1.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_homeStateOnDriveFragment)
        }

        naviguerButton1.setOnClickListener {

            val intent = Intent(context,NavigationActivity::class.java)
            startActivity(intent)

        }

    }
}