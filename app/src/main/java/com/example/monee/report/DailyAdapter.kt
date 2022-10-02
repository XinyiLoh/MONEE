package com.example.monee.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.monee.R
import com.example.monee.home.data.Categories

class DailyAdapter(private val dailyList: ArrayList<Categories>) :
    RecyclerView.Adapter<DailyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_dailylist_custom, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDaily = dailyList[position]
        holder.tvCat.text = currentDaily.category
        holder.tvFlow.text = currentDaily.type
        holder.tvAmount.text = currentDaily.amount.toString()
        holder.tvdate.text = currentDaily.date
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvFlow : TextView = itemView.findViewById(R.id.textView_flow)
        val tvCat : TextView = itemView.findViewById(R.id.textView_cat)
        val tvAmount : TextView = itemView.findViewById(R.id.textViewAmount)
        val tvdate : TextView = itemView.findViewById(R.id.textView_date)

    }

}