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
        val tvPlatform1: TextView = itemView.findViewById(R.id.tv_list_platform1)
        val tvPlatform2: TextView = itemView.findViewById(R.id.tv_list_platform2)
        val tvPlatform3: TextView = itemView.findViewById(R.id.tv_list_platform3)
        val tvPlatform4: TextView = itemView.findViewById(R.id.tv_list_platform4)
        val ivPlatform1: ImageView = itemView.findViewById(R.id.iv_platform1)
        val ivPlatform2: ImageView = itemView.findViewById(R.id.iv_platform2)
        val ivPlatform3: ImageView = itemView.findViewById(R.id.iv_platform3)
        val ivPlatform4: ImageView = itemView.findViewById(R.id.iv_platform4)
        val ivPlatform5: ImageView = itemView.findViewById(R.id.iv_platform5)
        val ivPlatform6: ImageView = itemView.findViewById(R.id.iv_platform6)
        val ivPlatform7: ImageView = itemView.findViewById(R.id.iv_platform7)

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

        for (i in 0..5) {
            if (i < game.platforms.size) {
                when (i) {
                    0 -> holder.tvPlatform1.text = game.platforms[i].platform.name
                    1 -> holder.tvPlatform2.text = game.platforms[i].platform.name
                    2 -> holder.tvPlatform3.text = game.platforms[i].platform.name
                    3 -> holder.tvPlatform4.text = game.platforms[i].platform.name
                }
            }
        }

        fun setPlatform(string: String, imageView: ImageView) {
            when (string) {
                "PC" -> imageView.setImageResource(R.drawable.windows_pc)
                "Xbox One" -> imageView.setImageResource(R.drawable.xbox)
                "PlayStation 4" -> imageView.setImageResource(R.drawable.sony_playstation)
                "Nintendo Switch" -> imageView.setImageResource(R.drawable.nintendo_switch)
                "macOS" -> imageView.setImageResource(R.drawable.mac_os)
                "Android" -> imageView.setImageResource(R.drawable.android_os)
                "Xbox" -> imageView.setImageResource(R.drawable.xbox)
                "iOS" -> imageView.setImageResource(R.drawable.app_ios)
                "Linux" -> imageView.setImageResource(R.drawable.linux_os)
            }
        }

        val platforms = arrayListOf(
            holder.ivPlatform1,
            holder.ivPlatform2,
            holder.ivPlatform3,
            holder.ivPlatform4,
            holder.ivPlatform5,
            holder.ivPlatform6,
            holder.ivPlatform7
        )

        for (i in 0..6)
            if (i < game.platforms.size) {
                setPlatform(game.platforms[i].platform.name, platforms[i])
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
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
