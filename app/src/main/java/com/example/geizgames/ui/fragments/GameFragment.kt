package com.example.geizgames.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.geizgames.R
import com.example.geizgames.adapter.GameAdapter
import com.example.geizgames.databinding.FragmentGameBinding
import com.example.geizgames.ui.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameFragment : Fragment(R.layout.fragment_game) {

    private val viewModel: GameViewModel by viewModels()
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var genre = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genre = requireArguments().getInt("genres")

        if (genre != 0) {
            viewModel.genres = genre
        }

        _binding = FragmentGameBinding.bind(view)
        val adapter = GameAdapter()

        binding.apply {
            rvGames.apply {
                itemAnimator = null
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }

        lifecycleScope.launch {
            viewModel.getGameListGenre().observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }
}
