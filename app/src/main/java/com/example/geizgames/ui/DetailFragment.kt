package com.example.geizgames.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.geizgames.R
import com.example.geizgames.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding

    private var name = ""
    private var backgroundImage = ""
    private var metacritic = 0
    private var platforms: Array<String> = arrayOf("" + "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = requireArguments().getString("name").toString()
        backgroundImage = requireArguments().getString("background_image").toString()
        metacritic = requireArguments().getInt("metacritic")
        platforms = requireArguments().getStringArray("platform") as Array<String>

        val imgUri = backgroundImage.toUri().buildUpon().scheme("https").build()

        binding.ivGameCover.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        binding.tvGameName.text = name

        binding.tvMetacritic.text = metacritic.toString()
        for (i in 0..9) {
            if (platforms.size > i) {
                binding.tvPlatformDetail1.text = platforms[i]
                binding.tvPlatformDetail2.text = platforms[i]
                binding.tvPlatformDetail3.text = platforms[i]
                binding.tvPlatformDetail4.text = platforms[i]
                binding.tvPlatformDetail5.text = platforms[i]
                binding.tvPlatformDetail6.text = platforms[i]
                binding.tvPlatformDetail7.text = platforms[i]
                binding.tvPlatformDetail8.text = platforms[i]
                binding.tvPlatformDetail9.text = platforms[i]
                binding.tvPlatformDetail10.text = platforms[i]
            }
        }
    }
}
