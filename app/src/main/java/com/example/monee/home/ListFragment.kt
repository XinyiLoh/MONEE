package com.example.monee.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.monee.R
import com.example.monee.databinding.FragmentListBinding
import com.example.monee.home.data.CategoriesViewModel
import com.example.monee.home.util.CategoriesAdapter


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val vm: CategoriesViewModel by activityViewModels()
    private val nav by lazy { findNavController() }


    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)


        binding.btnInsert.setOnClickListener { nav.navigate(R.id.InsertFragment) }
        binding.btnDeleteAll.setOnClickListener { deleteAll() }

        adapter = CategoriesAdapter() { holder, categories ->
            // Item click
            holder.root.setOnClickListener {
                nav.navigate(R.id.UpdateFragment, bundleOf("id" to categories.id))

            }
            // Delete button click
            holder.btnDelete.setOnClickListener {delete(categories.id.toString()) }
        }
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        vm.getAll().observe(viewLifecycleOwner){
            adapter.submitList(it)
            binding.txtCount.text = "${it.size} transaction(s)"
        }

        return binding.root

    }

    private fun deleteAll() {
        vm.deleteAll()

    }

    private fun delete(id: String) {

        /*Firebase.firestore
            .collection("categories")
            .document(id)
            .delete()*/
        vm.delete(id)
    }

    private fun toast (text: String){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
    }

}