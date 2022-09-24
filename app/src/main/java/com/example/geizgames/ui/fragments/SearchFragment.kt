package com.example.geizgames.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geizgames.adapter.SearchAdapter
import com.example.geizgames.databinding.FragmentSearchBinding
import com.example.geizgames.ui.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Weise der ViewModel Variablen im XML Layout das ViewModel zu
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        // Beobachte den TextInput und rufe die Daten aus der API ab
        viewModel.inputText.observe(viewLifecycleOwner) {
            viewModel.loadSearchData(it)
        }

        // Sobald die Daten aus der API geladen sind, setze einen neuen Adapter der RV
        viewModel.games.observe(viewLifecycleOwner) {
            binding.rvGameSearch.adapter = SearchAdapter(it)
        }
    }
}
