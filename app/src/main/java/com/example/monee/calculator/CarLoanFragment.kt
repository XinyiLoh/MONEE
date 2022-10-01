package com.example.monee.calculator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.monee.R
import com.example.monee.databinding.FragmentCarLoanBinding


class CarLoanFragment : Fragment() {

    private var _binding : FragmentCarLoanBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarLoanBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_loan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val manager: PackageManager = context?.packageManager!!
        //val appInfo = manager.getPackageInfo(context?.packageName!!, PackageManager.GET_ACTIVITIES)

        binding.carLoanResource.setOnClickListener {
            val intent = Intent(context, IMoneyCarLoanActivity::class.java)
            startActivity(intent)
        }
    }

}