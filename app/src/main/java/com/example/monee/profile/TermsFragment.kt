package com.example.monee.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.monee.R
import com.example.monee.databinding.FragmentProfileBinding
import com.example.monee.databinding.TermsAndConditionsBinding

class TermsFragment  : Fragment() {

    private var _binding: TermsAndConditionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_TermsFragment_to_ProfileFragment)
            //val fragment = TermsFragment()
            //val transaction = fragmentManager?.beginTransaction()
            //transaction?.replace(R.id.navigation,fragment)?.commit()
        }
    }
}