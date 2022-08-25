package com.example.geizgames.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.geizgames.adapter.GameAdapter
import com.example.geizgames.databinding.FragmentGameBinding
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.loadData()
        }

        binding.rvGames.setHasFixedSize(true)

        viewModel.games.observe(viewLifecycleOwner) {
            binding.rvGames.adapter = GameAdapter(it)
        }
    }
}
