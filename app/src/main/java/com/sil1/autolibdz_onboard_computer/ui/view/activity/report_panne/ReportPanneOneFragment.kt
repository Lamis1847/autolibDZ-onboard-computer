package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.sil1.autolibdz_onboard_computer.R
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_popup_panne.*
import kotlinx.android.synthetic.main.fragment_report_panne_one.*


class ReportPanneOneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popup_panne, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}