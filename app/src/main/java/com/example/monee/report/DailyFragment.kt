package com.example.monee.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.monee.R
import com.example.monee.databinding.FragmentDailyBinding
import com.example.monee.home.data.Categories
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_daily.*
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Categories(
    val amount: String = "",
    val type: String = "",
    val category: String = "",
    val date: String = ""
)

class DailyFragment : Fragment() {

    private var _binding : FragmentDailyBinding? = null
    private val binding get() = _binding!!

    private lateinit var dailyRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var dailyList: ArrayList<Categories>

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

        val db = Firebase.firestore

        //val query = db.collection("categories").whereEqualTo()

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val curDate: String = java.lang.String.valueOf(day)
            val year = java.lang.String.valueOf(year)
            val month = java.lang.String.valueOf(month)

            Log.d("date", curDate + year + month)

            val dateSelected = curDate + "/" + month + "/" + year
            //getDailyRecord(dateSelected)

            val query = db.collection("categories").whereEqualTo("date",dateSelected)


        }
    }

    //https://www.youtube.com/watch?v=DW-d0kalMvU
    //
    private fun getDailyRecord(string: String){
        dailyRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        //val firestore = FirebaseFirestore.getInstance()

        //dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        //dbRef.addValueEventListener(object : ValueEventListener{override fun onDataChange(snapshot: DataSnapshot) {
        //        dailyList.clear()
        //        if (snapshot.exists()){
        //            for (empSnap in snapshot.children){
        //                val dailyData = empSnap.getValue(Categories::class.java)
        //                dailyList.add(dailyData!!)
        //            }
         //           val mAdapter = DailyAdapter(dailyList)
          //          dailyRecyclerView.adapter = mAdapter

//                    dailyRecyclerView.visibility = View.VISIBLE
  //                  tvLoadingData.visibility = View.GONE
    //            }
      //      }

        //    override fun onCancelled(error: DatabaseError) {
          //      Log.w("Error", databaseError.toString())
            //}

        //})

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