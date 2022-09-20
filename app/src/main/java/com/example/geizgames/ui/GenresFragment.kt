package com.example.geizgames.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geizgames.adapter.GenresAdapter
import com.example.geizgames.databinding.FragmentGenresBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentGenresBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenresBinding.inflate(inflater, container, false)
        viewModel.loadGenresData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        // Sobald die Daten aus der API geladen sind, setze einen neuen Adapter der RV
        viewModel.games.observe(viewLifecycleOwner) {
            binding.rvGenres.adapter = GenresAdapter(it)
        }
    }
}