package com.example.geizgames.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.geizgames.R
import com.example.geizgames.data.models.gameResults.Screens
import com.example.geizgames.databinding.ListImageitemBinding
import com.example.geizgames.ui.fragments.DetailFragmentDirections

class ImageAdapter(
    private val dataset: List<Screens>?
) : RecyclerView.Adapter<ImageAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListImageitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListImageitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset?.get(position)

        val imgUri = item?.image?.toUri()?.buildUpon()?.scheme("https")?.build()

        holder.binding.ivImage.load(imgUri) {
            crossfade(true)
            crossfade(1000)
            transformations(RoundedCornersTransformation(10f))
            error(R.drawable.broken_img)
        }
        val imageUri = item?.image.toString()

        holder.binding.cvImage.setOnClickListener {
            if (item != null) {
                it.findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragmentToImageDetailFragment(
                        imageUri
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset!!.size
    }
}
