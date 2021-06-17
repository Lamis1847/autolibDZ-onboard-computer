package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.PanneRepository
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_description.view.*

class DescriptionFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View = inflater.inflate(R.layout.fragment_description, container, false)
        rootView.button2.setOnClickListener {
            val text = editTextDescription.text.toString()

            val preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            val long = preferences?.getFloat("panneLong",1F)
            val lal = preferences?.getFloat("panneLal",1F)

            var reportPanne = PanneRepository.Companion
            reportPanne.putPanne(requireContext(),1837, text, long, lal)

            dismiss()
        }

        return rootView
    }

}