package com.example.monee.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.monee.R
import com.example.monee.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import my.edu.tarc.mycontact.model.Categories

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding ?= null


    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnList.setOnClickListener { findNavController().navigate(R.id.fragment_list) }
        binding.btnRead.setOnClickListener { read() }
        binding.btnSet.setOnClickListener { set() }
        binding.btnUpdate.setOnClickListener { update() }
        binding.btnDelete.setOnClickListener { delete() }

        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    private fun read() {
        Firebase.firestore
            .collection("categories").
            get().
            addOnSuccessListener { snap -> val list = snap.toObjects<Categories>()
            var result = ""
            list.forEach{ f -> result += "${f.amount} ${f.category} ${f.type}\n"}
                binding.txtResult.text = result
            }

    }

    private fun set() {
        // TODO

    }

    private fun update() {
        // TODO

    }

    private fun delete() {
        // TODO

    }


}

