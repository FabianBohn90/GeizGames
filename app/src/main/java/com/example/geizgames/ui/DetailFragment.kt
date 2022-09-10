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
    private var platforms: Array<String> = arrayOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
    private var tags: Array<String> = arrayOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

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
        tags = requireArguments().getStringArray("tags") as Array<String>

        val imgUri = backgroundImage.toUri().buildUpon().scheme("https").build()

        binding.ivGameCover.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }

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

        binding.tvTagDetail1.text = tags[0] ; if (tags[0] == "") binding.tvTagDetail1.visibility = View.GONE ; binding.tvTagDetail16.text = tags[15] ; if (tags[15] == "") binding.tvTagDetail16.visibility = View.GONE
        binding.tvTagDetail2.text = tags[1] ; if (tags[1] == "") binding.tvTagDetail2.visibility = View.GONE ; binding.tvTagDetail17.text = tags[16] ; if (tags[16] == "") binding.tvTagDetail17.visibility = View.GONE
        binding.tvTagDetail3.text = tags[2] ; if (tags[2] == "") binding.tvTagDetail3.visibility = View.GONE ; binding.tvTagDetail18.text = tags[17] ; if (tags[17] == "") binding.tvTagDetail18.visibility = View.GONE
        binding.tvTagDetail4.text = tags[3] ; if (tags[3] == "") binding.tvTagDetail4.visibility = View.GONE ; binding.tvTagDetail19.text = tags[18] ; if (tags[18] == "") binding.tvTagDetail19.visibility = View.GONE
        binding.tvTagDetail5.text = tags[4] ; if (tags[4] == "") binding.tvTagDetail5.visibility = View.GONE ; binding.tvTagDetail20.text = tags[19] ; if (tags[19] == "") binding.tvTagDetail20.visibility = View.GONE
        binding.tvTagDetail6.text = tags[5] ; if (tags[5] == "") binding.tvTagDetail6.visibility = View.GONE ; binding.tvTagDetail21.text = tags[20] ; if (tags[20] == "") binding.tvTagDetail21.visibility = View.GONE
        binding.tvTagDetail7.text = tags[6] ; if (tags[6] == "") binding.tvTagDetail7.visibility = View.GONE ; binding.tvTagDetail22.text = tags[21] ; if (tags[21] == "") binding.tvTagDetail22.visibility = View.GONE
        binding.tvTagDetail8.text = tags[7] ; if (tags[7] == "") binding.tvTagDetail8.visibility = View.GONE ; binding.tvTagDetail23.text = tags[22] ; if (tags[22] == "") binding.tvTagDetail23.visibility = View.GONE
        binding.tvTagDetail9.text = tags[8] ; if (tags[8] == "") binding.tvTagDetail9.visibility = View.GONE ; binding.tvTagDetail24.text = tags[23] ; if (tags[23] == "") binding.tvTagDetail24.visibility = View.GONE
        binding.tvTagDetail10.text = tags[9] ; if (tags[9] == "") binding.tvTagDetail10.visibility = View.GONE ; binding.tvTagDetail25.text = tags[24] ; if (tags[24] == "") binding.tvTagDetail25.visibility = View.GONE
        binding.tvTagDetail11.text = tags[10] ; if (tags[10] == "") binding.tvTagDetail11.visibility = View.GONE ; binding.tvTagDetail26.text = tags[25] ; if (tags[25] == "") binding.tvTagDetail26.visibility = View.GONE
        binding.tvTagDetail12.text = tags[11] ; if (tags[11] == "") binding.tvTagDetail12.visibility = View.GONE ; binding.tvTagDetail27.text = tags[26] ; if (tags[26] == "") binding.tvTagDetail27.visibility = View.GONE
        binding.tvTagDetail13.text = tags[12] ; if (tags[12] == "") binding.tvTagDetail13.visibility = View.GONE ; binding.tvTagDetail28.text = tags[27] ; if (tags[27] == "") binding.tvTagDetail28.visibility = View.GONE
        binding.tvTagDetail14.text = tags[13] ; if (tags[13] == "") binding.tvTagDetail14.visibility = View.GONE ; binding.tvTagDetail29.text = tags[28] ; if (tags[28] == "") binding.tvTagDetail29.visibility = View.GONE
        binding.tvTagDetail15.text = tags[14] ; if (tags[14] == "") binding.tvTagDetail15.visibility = View.GONE ; binding.tvTagDetail30.text = tags[29] ; if (tags[29] == "") binding.tvTagDetail30.visibility = View.GONE
    }
}
