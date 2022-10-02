package com.example.geizgames.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.load
import com.example.geizgames.R
import com.example.geizgames.databinding.FragmentImagedetailBinding
import com.example.geizgames.ui.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: FragmentImagedetailBinding

    private var imageUri = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_imagedetail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageUri = requireArguments().getString("imageuri").toString()

        val imgUri = imageUri.toUri().buildUpon().scheme("https").build()

        binding.ivScreenDetail.load(imgUri) {
            crossfade(true)
            crossfade(1000)
            error(R.drawable.broken_img)
        }

        binding.ibClose.setOnClickListener {
            it.findNavController().navigateUp()
        }
    }
}
