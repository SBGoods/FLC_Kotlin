package com.goods.android.flc_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goods.android.flc_kotlin.databinding.FragmentHomeBinding
import com.goods.android.flc_kotlin.databinding.FragmentScoreHomeBinding

class ScoreHomeFragment : Fragment() {
    private var _binding: FragmentScoreHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}