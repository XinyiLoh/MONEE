package com.example.monee.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.monee.R
import com.example.monee.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuTnc.setOnClickListener {
           // findNavController().navigate(R.id.action_ProfileFragment_to_TermsFragment)
            //val fragment = TermsFragment()
            //val transaction = fragmentManager?.beginTransaction()
            //transaction?.replace(R.id.navigation,fragment)?.commit()
        }
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.menuGoals.setOnClickListener { setGoals() }
        //binding.menuEditprofile.setOnClickListener { editProfile() }
        //binding.menuChangepw.setOnClickListener { changePassword() }
        //binding.btnLogout.setOnClickListener { logout() }

        binding.menuTnc.setOnClickListener {

        }
    }*/

    /*private fun setGoals() {

    }

    private fun editProfile() {

    }

    private fun changePassword() {

    }

    private fun logout() {

    }*/
}