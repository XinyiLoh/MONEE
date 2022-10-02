package com.example.monee.report

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.monee.R
import com.example.monee.databinding.FragmentHomeBinding
import com.example.monee.databinding.FragmentSummaryBinding
import com.example.monee.home.data.Categories
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import java.util.Objects

data class Category2(
    val amount: Double = 0.00,
    val type: String = ""
)

class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_summary, container, false)

        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        readIncomeData()
        readExpensesData()

        binding.fabPrint.setOnClickListener{

        }
    }

    private fun readIncomeData(){

        Firebase.firestore
            .collection("categories")
            .whereEqualTo("type","Income")
            .get()
            .addOnSuccessListener { snap->

                val list = snap.toObjects<Category2>()
                val totalIncome: Double = list.sumOf { it.amount }

                binding.textViewTotalIncome.text = "RM" + totalIncome.toString()

            }.addOnFailureListener {
                Toast.makeText(context,"Failed to get data.",Toast.LENGTH_SHORT).show()
            }
    }

    private fun readExpensesData(){

        Firebase.firestore
            .collection("categories")
            .whereEqualTo("type","Outcome")
            .get()
            .addOnSuccessListener { snap->

                val list = snap.toObjects<Category2>()
                val totalOutcome: Double = list.sumOf { it.amount }

                binding.textViewTotalExpenses.text = "RM" + totalOutcome.toString()

            }.addOnFailureListener {
                Toast.makeText(context,"Failed to get data.",Toast.LENGTH_SHORT).show()
            }
    }

}