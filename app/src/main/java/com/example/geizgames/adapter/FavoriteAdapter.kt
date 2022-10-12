package com.example.geizgames.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.geizgames.R
import com.example.geizgames.data.models.Favorite
import com.example.geizgames.databinding.ListFavoriteitemBinding
import com.example.geizgames.ui.fragments.FavoritenFragmentDirections

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
        val imgUri = item.imageLink.toUri().buildUpon().scheme("https").build()
        val platformArray: Array<String> = arrayOf(
            item.platform1,
            item.platform2,
            item.platform3,
            item.platform4,
            item.platform5,
            item.platform6,
            item.platform7,
            item.platform8,
            item.platform9,
            item.platform10
        )
        val genreArray: Array<String> = arrayOf(
            item.genre1,
            item.genre2,
            item.genre3,
            item.genre4,
            item.genre5,
            item.genre6,
            item.genre7,
            item.genre8,
            item.genre9,
            item.genre10
        )

        holder.binding.apply {
            tvTitelFavo.text = item.name
            tvMetacriticFavo.text = item.metacritic.toString()
            imageView.load(imgUri) {
                crossfade(true)
                crossfade(1000)
                error(R.drawable.broken_img)
            }
        }

        if (item.metacritic != 0) {
            when (item.metacritic) {
                in 70..100 -> {
                    holder.binding.tvMetacriticFavo.setTextColor(Color.parseColor("#00BC51"))
                    holder.binding.tvMetacriticFavo.setBackgroundResource(R.drawable.rounded_corner_green)
                }
                in 40..69 -> {
                    holder.binding.tvMetacriticFavo.setTextColor(Color.parseColor("#FAB753"))
                    holder.binding.tvMetacriticFavo.setBackgroundResource(R.drawable.rounded_corner_orange)
                }
                in 0..39 -> {
                    holder.binding.tvMetacriticFavo.setTextColor(Color.parseColor("#E33314"))
                    holder.binding.tvMetacriticFavo.setBackgroundResource(R.drawable.rounded_corner_red)
                }
            }
        } else {
            holder.binding.tvMetacriticFavo.visibility = View.INVISIBLE
        }

        holder.binding.cvFavoItem.setOnClickListener {
            holder.itemView.findNavController().navigate(
                FavoritenFragmentDirections.actionFavoritenFragmentToDetailFragment(
                    item.name,
                    item.imageLink,
                    item.metacritic,
                    platformArray,
                    genreArray,
                    item.release,
                    item.slug,
                    item.id

                )
            )
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
