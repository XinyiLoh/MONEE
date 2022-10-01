package com.example.monee.calculator

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

    private fun calculateCarLoan() {

        val editTextCarPrice: EditText = binding.carPrice
        val editTextDownPayment: EditText = binding.downPayment
        val editTextYear: EditText = binding.loanPeriod
        val editTextInterestRate: EditText = binding.interestRate

        val car = editTextCarPrice.text.toString().toFloatOrNull()
        val down = editTextDownPayment.text.toString().toFloatOrNull()
        val yrs = editTextYear.text.toString().toFloatOrNull()
        val rate = editTextInterestRate.text.toString().toFloatOrNull()

        //$ Loan Amount = Car Price - Down Payment
        //$ Interest = Loan Amount * Interest Rate * Loan Period (in year)
        //$ Monthly Payment = (Loan Amount + Interest) / Loan Period (in month)

        if(car != null && down != null && yrs != null && rate !=null){
            var loanAmount = car - down
            var newRate = rate / 100
            var interest = loanAmount * newRate * yrs
            var monthPay = (loanAmount + interest) / ( yrs * 12 )

            textView_mr_price.text = "RM" + monthPay.toString()
        }else{
            Toast.makeText(context,"Incomplete input",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}