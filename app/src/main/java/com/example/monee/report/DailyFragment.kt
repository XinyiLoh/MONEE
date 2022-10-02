package com.example.monee.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monee.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.fragment_daily.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Category(
    val amount: Double = 0.00,
    val type: String = "",
    val category: String = "",
    val date: String = ""
)

class CategoriesVM(itemView: View):RecyclerView.ViewHolder(itemView)

class DailyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        //calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            //val curDate: String = java.lang.String.valueOf(day)
            //val year = java.lang.String.valueOf(year)
            //val month = java.lang.String.valueOf(month)

            //Log.d("date", curDate + year + month)

            //val dateSelected = curDate + "/" + month + "/" + year
            //getDailyRecord(dateSelected)

            val query = db.collection("categories")
            val options = FirestoreRecyclerOptions.Builder<Category>().setQuery(query,Category::class.java)
                .setLifecycleOwner(this).build()
            val adapter = object: FirestoreRecyclerAdapter<Category, CategoriesVM>(options){
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesVM {
                    val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_dailylist_custom, parent, false)
                    return CategoriesVM(view)
                }

                override fun onBindViewHolder(
                    holder: CategoriesVM,
                    position: Int,
                    model: Category
                ) {
                    val tvCat: TextView = holder.itemView.findViewById(R.id.textView_cat)
                    val tvFlow: TextView = holder.itemView.findViewById(R.id.textView_flow)
                    val tvAmount: TextView = holder.itemView.findViewById(R.id.textView_amount)
                    val tvDate: TextView = holder.itemView.findViewById(R.id.textView_date)

                    tvCat.text = model.category
                    tvFlow.text = model.type
                    tvAmount.text = model.amount.toString()
                    tvDate.text = model.date
                }

            }

            rvDaily.adapter=adapter
            rvDaily.layoutManager = LinearLayoutManager(context)

    }
}