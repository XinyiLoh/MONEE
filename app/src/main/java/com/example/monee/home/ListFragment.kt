package com.example.monee.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.monee.R
import com.example.monee.databinding.FragmentListBinding
import com.example.monee.home.data.CategoriesViewModel
import com.example.monee.home.util.CategoriesAdapter


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding?= null
    private val binding get() = _binding!!
    private val vm: CategoriesViewModel by activityViewModels()

    private lateinit var adapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.btnInsert.setOnClickListener {  findNavController().navigate(R.id.insertFragment) }
        binding.btnDeleteAll.setOnClickListener { deleteAll() }

        adapter = CategoriesAdapter() { holder, categories ->
            // Item click
            holder.root.setOnClickListener {
                findNavController().navigate(R.id.homeFragment, bundleOf("id" to categories.id))
            }
            // Delete button click
            holder.btnDelete.setOnClickListener { delete(categories.id.toString()) }
        }
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return binding.root
    }

    private fun deleteAll() {
        // TODO: Delete all

    }

    private fun delete(id: String) {
        // TODO: Delete

    }


}