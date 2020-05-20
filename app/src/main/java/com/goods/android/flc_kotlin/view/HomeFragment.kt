package com.goods.android.flc_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.goods.android.flc_kotlin.R
import com.goods.android.flc_kotlin.databinding.FragmentHomeBinding

class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding?.btnMCEasyLaunch?.setOnClickListener {  }

        binding.btnMCEasyLaunch.setOnClickListener {
           view?.findNavController()?.navigate(R.id.action_homeFragment_to_quizFragment)
        }

        return binding.root

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}