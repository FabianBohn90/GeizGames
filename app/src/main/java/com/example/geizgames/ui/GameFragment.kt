package com.example.geizgames.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.geizgames.R
import com.example.geizgames.adapter.GameAdapter
import com.example.geizgames.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameFragment : Fragment(R.layout.fragment_game) {

    private val viewModel: GameViewModel by viewModels()
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentGameBinding.bind(view)
        val adapter = GameAdapter(viewLifecycleOwner.lifecycleScope)

        binding.apply {
            rvGames.apply {
                itemAnimator = null
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }

        lifecycleScope.launch {
            viewModel.getGameList().observe(viewLifecycleOwner) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }

//        viewModel.games.observe(viewLifecycleOwner) {
//            binding.rvGames.adapter = GameAdapter(it)
//        }
            }
        }
    }
}
