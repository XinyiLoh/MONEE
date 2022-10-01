package com.example.monee.calculator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.monee.R
import com.example.monee.databinding.FragmentCarLoanBinding
import kotlinx.android.synthetic.main.fragment_car_loan.*


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

        binding.btnCalculateCarLoan.setOnClickListener{
            calculateCarLoan()
        }

        binding.carLoanResource.setOnClickListener {
            val intent = Intent(context, IMoneyCarLoanActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isNumeric(toCheck: String): Boolean {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        return toCheck.matches(regex)
    }

    private fun calculateCarLoan() {

        val carPrice = _binding?.vehiclePrice.toString()
        val downPayment = _binding?.downPayment.toString()
        val yrs = _binding?.loanPeriod.toString()
        val rate = _binding?.interestRate.toString()

        vehicle_price.helperText = isNumeric(carPrice).toString()
        down_payment.helperText = isNumeric(downPayment).toString()
        loan_period.helperText = isNumeric(yrs).toString()
        interest_rate.helperText = isNumeric(rate).toString()

        val checkCarPrice = vehicle_price.helperText == null
        val checkDownPayment = down_payment.helperText == null
        val checkLoanPeriod = loan_period.helperText == null
        val checkInterest = interest_rate.helperText == null

        if (checkCarPrice && checkDownPayment && checkLoanPeriod && checkInterest) {
            //var monthlyPayment = carPrice
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}