package com.example.monee.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.monee.R
import com.example.monee.databinding.FragmentDailyBinding
import com.example.monee.home.data.Categories
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_daily.*

class DailyFragment : Fragment() {

    private var _binding : FragmentDailyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDailyBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val curDate: String = java.lang.String.valueOf(day)
            val year = java.lang.String.valueOf(year)
            val month = java.lang.String.valueOf(month)

            Log.d("date", curDate + year + month)
        }
    }

    private fun setUpFireStore(){
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("categories").whereEqualTo("date","25/8/2022")
            .get()
            .addOnSuccessListener {
                Log.d("DATA",it.toObjects(Categories::class.java).toString())
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}