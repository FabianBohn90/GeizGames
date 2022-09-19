package com.example.geizgames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.geizgames.R
import com.example.geizgames.data.models.Results
import com.example.geizgames.databinding.ListGenresitemBinding

class GenresAdapter(
    private val dataset: List<Results>
) : RecyclerView.Adapter<GenresAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListGenresitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListGenresitemBinding.inflate(
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
            transformations(RoundedCornersTransformation(10f))
            error(R.drawable.broken_img)
        }

        holder.binding.tvGenresTitle.text = genre.name
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
