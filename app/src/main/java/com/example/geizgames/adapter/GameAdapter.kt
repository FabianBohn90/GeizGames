package com.example.geizgames.adapter

import android.graphics.Color
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
        val ivPlatform1: ImageView = itemView.findViewById(R.id.iv_platform1)
        val ivPlatform2: ImageView = itemView.findViewById(R.id.iv_platform2)
        val ivPlatform3: ImageView = itemView.findViewById(R.id.iv_platform3)
        val ivPlatform4: ImageView = itemView.findViewById(R.id.iv_platform4)
        val ivPlatform5: ImageView = itemView.findViewById(R.id.iv_platform5)
        val ivPlatform6: ImageView = itemView.findViewById(R.id.iv_platform6)
        val ivPlatform7: ImageView = itemView.findViewById(R.id.iv_platform7)
        val ivPlatform8: ImageView = itemView.findViewById(R.id.iv_platform8)
        val ivPlatform9: ImageView = itemView.findViewById(R.id.iv_platform9)

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

        fun setPlatform(int: Int, imageView: ImageView) {
            when (int) {
                4 -> imageView.setImageResource(R.drawable.windows_pc)
                1 -> imageView.setImageResource(R.drawable.xbox)
                80 -> imageView.setImageResource(R.drawable.xbox)
                186 -> imageView.setImageResource(R.drawable.xbox)
                16 -> imageView.setImageResource(R.drawable.ps3)
                18 -> imageView.setImageResource(R.drawable.ps4)
                187 -> imageView.setImageResource(R.drawable.sony_playstation)
                19 -> imageView.setImageResource(R.drawable.psvita)
                7 -> imageView.setImageResource(R.drawable.nintendo_switch)
                5 -> imageView.setImageResource(R.drawable.mac_os)
                21 -> imageView.setImageResource(R.drawable.android_os)
                3 -> imageView.setImageResource(R.drawable.app_ios)
                6 -> imageView.setImageResource(R.drawable.linux_os)
                14 -> imageView.setImageResource(R.drawable.xbox360)
                171 -> imageView.setImageResource(R.drawable.web)
            }
        }

        val platforms = arrayListOf(
            holder.ivPlatform1,
            holder.ivPlatform2,
            holder.ivPlatform3,
            holder.ivPlatform4,
            holder.ivPlatform5,
            holder.ivPlatform6,
            holder.ivPlatform7,
            holder.ivPlatform8,
            holder.ivPlatform9
        )

        for (i in 0..8)
            if (i < game.platforms.size) {
                setPlatform(game.platforms[i].platform.id, platforms[i])
            }

        if (game.metacritic > 70) {
            holder.tvGameMetacritic.setTextColor(Color.GREEN)
        }

        holder.tvGameMetacritic.text = game.metacritic.toString()

        holder.cardView.setOnClickListener {
            holder.itemView.findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToDetailFragment(
                    game.name,
                    game.background_image,
                    game.metacritic,
                    game.platforms[0].platform.name
                )
            )
        }
//      stop das recyclen der views
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
