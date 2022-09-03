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

        val imgUri = game.background_image?.toUri()?.buildUpon()?.scheme("https")?.build()

        holder.ivGame.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
            error(R.drawable.broken_img)
        }

        if (game.background_image == null) {
            holder.ivGame.setImageResource(R.drawable.broken_img)
        }
        holder.tvTitle.text = game.name

        fun setPlatform(int: Int, imageView: ImageView) {
            when (int) {
                4 -> {
                    imageView.setImageResource(R.drawable.windows_pc)
                    imageView.visibility = View.VISIBLE
                }
                1 -> {
                    imageView.setImageResource(R.drawable.xbox)
                    imageView.visibility = View.VISIBLE
                }
                80 -> {
                    imageView.setImageResource(R.drawable.xbox)
                    imageView.visibility = View.VISIBLE
                }
                186 -> {
                    imageView.setImageResource(R.drawable.xbox)
                    imageView.visibility = View.VISIBLE
                }
                16 -> {
                    imageView.setImageResource(R.drawable.ps3)
                    imageView.visibility = View.VISIBLE
                }
                18 -> {
                    imageView.setImageResource(R.drawable.ps4)
                    imageView.visibility = View.VISIBLE
                }
                187 -> {
                    imageView.setImageResource(R.drawable.sony_playstation)
                    imageView.visibility = View.VISIBLE
                }
                19 -> {
                    imageView.setImageResource(R.drawable.psvita)
                    imageView.visibility = View.VISIBLE
                }
                7 -> {
                    imageView.setImageResource(R.drawable.nintendo_switch)
                    imageView.visibility = View.VISIBLE
                }
                5 -> {
                    imageView.setImageResource(R.drawable.mac_os)
                    imageView.visibility = View.VISIBLE
                }
                21 -> {
                    imageView.setImageResource(R.drawable.android_os)
                    imageView.visibility = View.VISIBLE
                }
                3 -> {
                    imageView.setImageResource(R.drawable.app_ios)
                    imageView.visibility = View.VISIBLE
                }
                6 -> {
                    imageView.setImageResource(R.drawable.linux_os)
                    imageView.visibility = View.VISIBLE
                }
                14 -> {
                    imageView.setImageResource(R.drawable.xbox360)
                    imageView.visibility = View.VISIBLE
                }
                171 -> {
                    imageView.setImageResource(R.drawable.web)
                    imageView.visibility = View.VISIBLE
                }
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

        for (i in 0..8) {
            if (game.platforms != null) {
                if (i < game.platforms?.size!!) {
                    game.platforms?.get(i)?.platform?.id?.let { setPlatform(it, platforms[i]) }
                }
            }
        }

        if (game.metacritic != null) {
            when (game.metacritic) {
                in 70..100 -> {
                    holder.tvGameMetacritic.setTextColor(Color.parseColor("#80FEBC"))
                    holder.tvGameMetacritic.setBackgroundResource(R.drawable.rounded_corner_green)
                }
                in 40..69 -> {
                    holder.tvGameMetacritic.setTextColor(Color.parseColor("#FAB753"))
                    holder.tvGameMetacritic.setBackgroundResource(R.drawable.rounded_corner_orange)
                }
                in 0..39 -> {
                    holder.tvGameMetacritic.setTextColor(Color.parseColor("#E33314"))
                    holder.tvGameMetacritic.setBackgroundResource(R.drawable.rounded_corner_red)
                }
            }
        } else {
            holder.tvGameMetacritic.visibility = View.INVISIBLE
        }

        holder.tvGameMetacritic.text = game.metacritic.toString()

        holder.cardView.setOnClickListener {
            var img = ""
            var metacritic = 0
            var platformName = ""

            if (game.metacritic != null) metacritic = game.metacritic
            if (game.background_image != null) img = game.background_image
            if (game.platforms?.get(0) != null) platformName = game.platforms!![0].platform.name

            holder.itemView.findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToDetailFragment(
                    game.name,
                    img,
                    metacritic,
                    platformName
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
