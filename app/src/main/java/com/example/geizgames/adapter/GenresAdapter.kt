package com.example.geizgames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.geizgames.R
import com.example.geizgames.data.models.gameResults.Results
import com.example.geizgames.databinding.ListFilteritemBinding
import com.example.geizgames.ui.fragments.FilterFragmentDirections

class GenresAdapter(
    private val dataset: List<Results>
) : RecyclerView.Adapter<GenresAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListFilteritemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListFilteritemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val genre = dataset[position]

        val imgUri = genre.image_background?.toUri()?.buildUpon()?.scheme("https")?.build()

        holder.binding.ivGenresImg.load(imgUri) {
            error(R.drawable.broken_img)
        }

        holder.binding.tvGenresTitle.text = genre.name

        holder.binding.cvGenres.setOnClickListener {
            holder.itemView.findNavController().navigate(
                FilterFragmentDirections.actionFilterFragmentToGameFragment(
                    genre.id.toInt()
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
