package com.example.monee.home.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.monee.R
import com.example.monee.home.data.Categories


class CategoriesAdapter (
    val fn: (ViewHolder, Categories) -> Unit = { _, _ -> }
) : ListAdapter<Categories, CategoriesAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(a: Categories, b: Categories)    = a.id == b.id
        override fun areContentsTheSame(a: Categories, b: Categories) = a == b
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view
        val txtType    : TextView = view.findViewById(R.id.textViewType)
        val txtCategory  : TextView = view.findViewById(R.id.textViewCategory)
        val txtAmount   : TextView = view.findViewById(R.id.textViewAmount)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)

        holder.txtType.text   = category.type
        holder.txtCategory.text = category.category
        holder.txtAmount.text  = category.amount.toString()

        // TODO: Photo (blob to bitmap)


        fn(holder, category)
    }

}

