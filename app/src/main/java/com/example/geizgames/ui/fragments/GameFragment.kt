package com.example.geizgames.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.geizgames.R
import com.example.geizgames.adapter.GameAdapter
import com.example.geizgames.adapter.GameLoadStateAdapter
import com.example.geizgames.databinding.FragmentGameBinding
import com.example.geizgames.ui.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameFragment : Fragment(R.layout.fragment_game) {

    private val viewModel: GameViewModel by viewModels()
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var filterId = 0
    private var from = "go"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterId = requireArguments().getInt("filterid")
        from = requireArguments().getString("from").toString()

        if (filterId != 0) {
            viewModel.filterid = filterId
        }

        fun handleError(loadState: CombinedLoadStates) {
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error

                ?: loadState.source.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(requireContext(), "${it.error}", Toast.LENGTH_LONG).show()
            }
        }

        _binding = FragmentGameBinding.bind(view)
        val adapter = GameAdapter()

        binding.apply {
            rvGames.apply {
                itemAnimator = null
                setHasFixedSize(true)
                this.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = GameLoadStateAdapter { adapter.retry() },
                    footer = GameLoadStateAdapter { adapter.retry() }
                )

                adapter.addLoadStateListener { loadState ->
                    this.isVisible = loadState.source.refresh is LoadState.NotLoading
                    progressBarMain?.isVisible = loadState.source.refresh is LoadState.Loading
                    btnRetryMain?.isVisible = loadState.source.refresh is LoadState.Error
                    handleError(loadState)
                }
                btnRetryMain?.setOnClickListener {
                    adapter.retry()
                }
            }

            lifecycleScope.launch {
                viewModel.getGameListGenre().observe(viewLifecycleOwner) {
                    it?.let {
                        adapter.submitData(lifecycle, it)
                    }
                }
            }

            if (from == "Platform") {
                lifecycleScope.launch {
                    viewModel.getGameListPlatform().observe(viewLifecycleOwner) {
                        it?.let {
                            adapter.submitData(lifecycle, it)
                        }
                    }
                }
            }
        }
    }
}
