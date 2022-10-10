package com.example.geizgames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.geizgames.R
import com.example.geizgames.data.models.Favorite
import com.example.geizgames.databinding.ListFavoriteitemBinding

class FavoriteAdapter(
    private val dataset: List<Favorite>
) : RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListFavoriteitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListFavoriteitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvTitelFavo.text = item.name
        holder.binding.tvMetacriticFavo.text = item.metacritic.toString()

        val imgUri = item.imageLink?.toUri()?.buildUpon()?.scheme("https")?.build()

        holder.binding.imageView.load(imgUri) {
            crossfade(true)
            crossfade(1000)
            error(R.drawable.broken_img)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
