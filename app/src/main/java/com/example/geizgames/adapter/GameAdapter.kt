package com.example.geizgames.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.geizgames.R
import com.example.geizgames.data.models.Game
import com.example.geizgames.ui.GameFragmentDirections

class GameAdapter(
    private val dataset: List<Game>

) : RecyclerView.Adapter<GameAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGame: ImageView = itemView.findViewById(R.id.imageView)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_game_titel)
        val tvReleaseDate: TextView = itemView.findViewById(R.id.tv_list_release)
        val tvGameMetacritic: TextView = itemView.findViewById(R.id.tv_game_score)
        val cardView: CardView = itemView.findViewById(R.id.cv_listitem_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = dataset[position]

        val imgUri = game.background_image.toUri().buildUpon().scheme("https").build()

        holder.ivGame.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }
        holder.tvTitle.text = game.name
        holder.tvReleaseDate.text = game.released
        holder.tvGameMetacritic.text = game.metacritic.toString()

        holder.cardView.setOnClickListener {
            holder.itemView.findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToDetailFragment()
            )
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
