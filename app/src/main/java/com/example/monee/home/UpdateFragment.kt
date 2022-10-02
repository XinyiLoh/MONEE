package com.example.monee.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.monee.databinding.FragmentUpdateBinding
import com.example.monee.home.data.Categories
import com.example.monee.home.data.CategoriesViewModel
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding ? = null
    private val binding get() = _binding!!
    private val vm: CategoriesViewModel by activityViewModels()
    private val nav by lazy { findNavController() }


    private val id by lazy { requireArguments().getString("id") ?: "" }
    private val formatter = SimpleDateFormat("dd MMMM yyyy '-' hh:mm:ss a", Locale.getDefault())


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        reset()
        binding.btnReset.setOnClickListener { reset() }
        binding.btnSubmit.setOnClickListener { submit() }
        binding.btnDelete.setOnClickListener { delete() }

        return binding.root
    }

    private fun reset() {
        // TODO: Load data

    }

    /*private fun load(f: Categories) {
        binding.txtId.text = f.id
        binding.edtName.setText(f.name)
        binding.edtAge.setText(f.age.toString())

        // TODO: Load photo and date


        binding.edtName.requestFocus()
    }*/

    private fun submit() {
        // TODO: Update (set)

    }

    private fun delete() {
        // TODO: Delete

    }

}


