package com.example.geizgames.ui.fragments

import android.os.Bundle
import android.view.* // ktlint-disable no-wildcard-imports
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.load
import com.example.geizgames.R
import com.example.geizgames.databinding.FragmentImagedetailBinding
import com.example.geizgames.ui.GameViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: FragmentImagedetailBinding
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    private var imageUri = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_imagedetail,
            container,
            false
        )
//        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
        return binding.root
    }
//    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
//        scaleGestureDetector.onTouchEvent(motionEvent)
//        return true
//    }
//
//    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
//            scaleFactor *= scaleGestureDetector.scaleFactor
//            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
//            binding.ivScreenDetail.scaleX = scaleFactor
//            binding.ivScreenDetail.scaleY = scaleFactor
//            return true
//        }
//    }

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
