package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.PanneRepository
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_navigation_one.*
import kotlinx.android.synthetic.main.fragment_panne_ext.*
import kotlinx.android.synthetic.main.fragment_panne_ext.view.*

class PanneExtFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View = inflater.inflate(R.layout.fragment_panne_ext, container, false)

        rootView.button.setOnClickListener {
            val selectedRadio = radioGroup2.checkedRadioButtonId
            val radio = rootView.findViewById<RadioButton>(selectedRadio)

            val result = radio.text.toString()

            val preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            val long = preferences?.getFloat("panneLong",1F)
            val lal = preferences?.getFloat("panneLal",1F)

            var reportPanne = PanneRepository.Companion
            reportPanne.putPanne(requireContext(),123333, result, long, lal)

            dismiss()
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}