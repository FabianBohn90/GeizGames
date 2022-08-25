package com.example.geizgames.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geizgames.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding
}
