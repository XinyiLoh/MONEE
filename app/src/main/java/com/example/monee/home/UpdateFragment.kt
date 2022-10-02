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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.monee.databinding.FragmentUpdateBinding
import com.example.monee.home.data.Categories
import com.example.monee.home.data.CategoriesViewModel
import com.example.monee.home.util.errorDialog
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding ? = null
    private val binding get() = _binding!!
    private val vm: CategoriesViewModel by activityViewModels()
    private val nav by lazy { findNavController() }

    private val id by lazy { requireArguments().getString("id") ?: "" }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        reset()
        binding.btnReset.setOnClickListener { reset() }
        binding.btnSubmit.setOnClickListener { submit() }
        binding.btnDelete.setOnClickListener { delete() }
        binding.buttonBack.setOnClickListener { nav.navigateUp() }

        return binding.root
    }

    private fun reset() {
        lifecycleScope.launch {
            val f = vm.get(id)
            if(f == null){
                nav.navigateUp()
                return@launch
            }

            load(f)
        }

    }

    private fun load(f: Categories) {
        binding.txtId.text = f.id.toString()
        binding.edtCategory.setText(f.category)
        binding.edtAmount.setText(f.amount.toString())
        binding.edtDate.setText(f.date)
        binding.edtSpinnerType.setSelection(1)
        binding.edtDate.requestFocus()
    }

    private fun submit() {
        val c = Categories(
            id = id.toInt(),
            amount = binding.edtAmount.text.toString().toDouble(),
            type = binding.edtSpinnerType.selectedItem.toString().trim(),
            category = binding.edtCategory.text.toString().trim(),
            date = binding.edtDate.text.toString()
        )

        lifecycleScope.launch {
            val err = vm.validate(c)
            if (err != "") {
                errorDialog(err)
                return@launch
            }

        }

        vm.set(c)
        nav.navigateUp()




    }

    private fun delete() {
        vm.delete(id)
        nav.navigateUp()

    }

}


