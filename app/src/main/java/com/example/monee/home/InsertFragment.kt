package com.example.monee.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.monee.databinding.FragmentInsertBinding
import com.example.monee.home.data.Categories
import com.example.monee.home.data.CategoriesViewModel
import com.example.monee.home.util.errorDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class InsertFragment : Fragment() {

    private var _binding: FragmentInsertBinding? = null
    private val binding get() = _binding!!
    private val nav by lazy { findNavController() }
    private val vm: CategoriesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInsertBinding.inflate(inflater, container, false)

        reset()
        binding.btnReset.setOnClickListener { reset() }
        binding.btnSubmit.setOnClickListener { submit() }
        binding.btnBack.setOnClickListener{ nav.navigateUp()}

        return binding.root
    }


    private fun reset() {
        binding.editId.text.clear()
        binding.editAmount.text.clear()
        binding.editCategory.text.clear()
        binding.spinnerType.setSelection(0)
        binding.editId.requestFocus()
        binding.editDate.text.clear()
    }

    private fun submit() {

        val data = Categories(
            id = binding.editId.text.toString().toInt()?: 0,
            amount = binding.editAmount.text.toString().toDoubleOrNull() ?: 0.0,
            type = binding.spinnerType.selectedItem.toString().trim(),
            category = binding.editCategory.text.toString().trim(),
            date = binding.editDate.text.toString()
        )

        lifecycleScope.launch {
            val err = vm.validate(data)
            if (err != "") {
                errorDialog(err)
                return@launch
            }


            vm.set(data)
            nav.navigateUp()

        }

    }
}