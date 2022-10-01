package com.example.monee.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.monee.R
import com.example.monee.databinding.FragmentHomeBinding
import com.example.monee.home.data.Categories
import com.example.monee.home.data.CategoriesViewModel


class fragment_list : Fragment() {

    private var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    private val vm: CategoriesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }


}