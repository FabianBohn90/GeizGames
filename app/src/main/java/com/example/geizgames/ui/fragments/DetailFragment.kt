package com.example.geizgames.ui.fragments

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
import com.example.geizgames.adapter.ImageAdapter
import com.example.geizgames.adapter.ShopAdapter
import com.example.geizgames.databinding.FragmentDetailBinding
import com.example.geizgames.ui.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding

    private var index = 0
    private var name = ""
    private var slug = ""
    private var release = ""
    private var backgroundImage = ""
    private var metacritic = 0
    private var platforms: Array<String> = arrayOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
    private var tags: Array<String> = arrayOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            slug = it.getString("slug").toString()
            name = it.getString("name").toString()
            backgroundImage = it.getString("background_image").toString()
            metacritic = it.getInt("metacritic")
            platforms = it.getStringArray("platform") as Array<String>
            tags = it.getStringArray("tags") as Array<String>
            release = it.getString("release").toString()
            index = it.getInt("index")
        }
//        viewModel.slug.value = slug
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.slug.value = slug


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imgUri = backgroundImage.toUri().buildUpon().scheme("https").build()

        binding.ivGameCover.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        viewModel.slug.observe(viewLifecycleOwner) {
            viewModel.loadShopData(it)
            viewModel.loadImageData(it)
        }

        viewModel.shops.observe(viewLifecycleOwner) {
            binding.rvShops.adapter = ShopAdapter(it)
        }

        viewModel.images.observe(viewLifecycleOwner) {
            binding.rvScreenshots.adapter = ImageAdapter(it)
        }

        binding.tvRelease.text = release

        binding.tvGameName.text = name

        binding.tvMetacritic.text = metacritic.toString()

        binding.tvPlatformDetail1.text = platforms[0] ; if (platforms[0] == "") binding.tvPlatformDetail1.visibility = View.GONE
        binding.tvPlatformDetail2.text = platforms[1] ; if (platforms[1] == "") binding.tvPlatformDetail2.visibility = View.GONE
        binding.tvPlatformDetail3.text = platforms[2] ; if (platforms[2] == "") binding.tvPlatformDetail3.visibility = View.GONE
        binding.tvPlatformDetail4.text = platforms[3] ; if (platforms[3] == "") binding.tvPlatformDetail4.visibility = View.GONE
        binding.tvPlatformDetail5.text = platforms[4] ; if (platforms[4] == "") binding.tvPlatformDetail5.visibility = View.GONE
        binding.tvPlatformDetail6.text = platforms[5] ; if (platforms[5] == "") binding.tvPlatformDetail6.visibility = View.GONE
        binding.tvPlatformDetail7.text = platforms[6] ; if (platforms[6] == "") binding.tvPlatformDetail7.visibility = View.GONE
        binding.tvPlatformDetail8.text = platforms[7] ; if (platforms[7] == "") binding.tvPlatformDetail8.visibility = View.GONE
        binding.tvPlatformDetail9.text = platforms[8] ; if (platforms[8] == "") binding.tvPlatformDetail9.visibility = View.GONE
        binding.tvPlatformDetail10.text = platforms[9] ; if (platforms[9] == "") binding.tvPlatformDetail10.visibility = View.GONE

        binding.tvTagDetail1.text = tags[0] ; if (tags[0] == "") binding.tvTagDetail1.visibility = View.GONE
        binding.tvTagDetail1.text = tags[1] ; if (tags[1] == "") binding.tvTagDetail2.visibility = View.GONE
        binding.tvTagDetail3.text = tags[2] ; if (tags[2] == "") binding.tvTagDetail3.visibility = View.GONE
        binding.tvTagDetail4.text = tags[3] ; if (tags[3] == "") binding.tvTagDetail4.visibility = View.GONE
        binding.tvTagDetail5.text = tags[4] ; if (tags[4] == "") binding.tvTagDetail5.visibility = View.GONE
        binding.tvTagDetail6.text = tags[5] ; if (tags[5] == "") binding.tvTagDetail6.visibility = View.GONE
        binding.tvTagDetail7.text = tags[6] ; if (tags[6] == "") binding.tvTagDetail7.visibility = View.GONE
        binding.tvTagDetail8.text = tags[7] ; if (tags[7] == "") binding.tvTagDetail8.visibility = View.GONE
        binding.tvTagDetail9.text = tags[8] ; if (tags[8] == "") binding.tvTagDetail9.visibility = View.GONE
        binding.tvTagDetail10.text = tags[9] ; if (tags[9] == "") binding.tvTagDetail10.visibility = View.GONE
    }
}
