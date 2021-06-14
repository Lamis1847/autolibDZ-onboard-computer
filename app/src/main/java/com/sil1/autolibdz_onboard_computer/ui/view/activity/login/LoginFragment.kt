package com.sil1.autolibdz_onboard_computer.ui.view.activity.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.CodePinRepository
import kotlinx.android.synthetic.main.fragment_code_pin.*
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retourMaint.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_codePinFragment)
        }

    }

}