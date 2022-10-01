package com.example.monee.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.monee.R
import com.example.monee.databinding.FragmentHomeBinding
import com.example.monee.home.data.Categories
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding ?= null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.btnList.setOnClickListener { findNavController().navigate(R.id.homeFragment) }

        binding.btnRead.setOnClickListener {
            read()

        }

        //binding.btnSet.setOnClickListener { set() }
        binding.btnUpdate.setOnClickListener { update() }
        binding.buttonDelete.setOnClickListener { delete() }
        return binding.root

    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    private fun read() {

        Firebase.firestore
            .collection("categories")
            .get()
            .addOnSuccessListener { snap ->
                val list = snap.toObjects<Categories>()
                var result = ""
                list.forEach{ f -> result += "${f.id}},${f.amount} ${f.category} ${f.type}\n"}
                binding.txtResult.text = result

            }

    }

    /*private fun set() {
        val f = Categories(2,17.9,"Income","Shopping")

        Firebase.firestore
            .collection("categories")
            .document("2")
            .set(f)
            .addOnSuccessListener { toast("Recorded Inserted") }


    }*/

    private fun update() {

        Firebase.firestore
            .collection("categories")
            .document("2")
            .update("amount",8.90,"category","Drink")
            .addOnSuccessListener { toast("Recorded Updated") }

    }

    private fun delete() {
        Firebase.firestore
            .collection("categories")
            .document("2")
            .delete()
            .addOnSuccessListener { toast("Record Deleted") }

    }

    private fun toast (text: String){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show()
    }


}

