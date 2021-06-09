package com.sil1.autolibdz_onboard_computer.ui.view.activity


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

class CodePinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_code_pin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pinEntry = getView()?.findViewById<View>(R.id.txt_pin_entry) as PinEntryEditText
        pinEntry.setOnPinEnteredListener { str ->
            validerBoutton.setOnClickListener {
                var loginActivity = CodePinRepository.Companion
                loginActivity.codePin(requireContext(),str.toString())
            }
        }

        maintenance.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_codePinFragment_to_loginFragment)
        }

    }

}