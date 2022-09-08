package com.example.geizgames.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.geizgames.R
import com.example.geizgames.data.models.Game
import com.example.geizgames.databinding.ListItemBinding
import com.example.geizgames.ui.GameFragmentDirections
import kotlinx.coroutines.CoroutineScope

class GameAdapter(lifecycleScope: CoroutineScope) : PagingDataAdapter<Game, GameAdapter.ItemViewHolder>(diffCallback) {

    inner class ItemViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = getItem(position)

        val imgUri = game?.background_image?.toUri()?.buildUpon()?.scheme("https")?.build()

        holder.binding.ivGame.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
            error(R.drawable.broken_img)
        }

        if (game != null) {
            if (game.background_image == null) {
                holder.binding.ivGame.setImageResource(R.drawable.broken_img)
            }
        }
        if (game != null) {
            holder.binding.tvGameTitel.text = game.name
        }

        fun setPlatform(int: Int, imageView: ImageView) {
            when (int) {
                15 -> {
                    imageView.setImageResource(R.drawable.playstation_2)
                    imageView.visibility = View.VISIBLE
                }
                106 -> {
                    imageView.setImageResource(R.drawable.dreamcast)
                    imageView.visibility = View.VISIBLE
                }
                24 -> {
                    imageView.setImageResource(R.drawable.nintendo_game_boy)
                    imageView.visibility = View.VISIBLE
                }
                105 -> {
                    imageView.setImageResource(R.drawable.nintendo_gamecube)
                    imageView.visibility = View.VISIBLE
                }
                10 -> {
                    imageView.setImageResource(R.drawable.nintendo_wiiu)
                    imageView.visibility = View.VISIBLE
                }
                83 -> {
                    imageView.setImageResource(R.drawable.nintendo64)
                    imageView.visibility = View.VISIBLE
                }

                11 -> {
                    imageView.setImageResource(R.drawable.nintendo_wii)
                    imageView.visibility = View.VISIBLE
                }

                8 -> {
                    imageView.setImageResource(R.drawable.nintendo_ds)
                    imageView.visibility = View.VISIBLE
                }
                9 -> {
                    imageView.setImageResource(R.drawable.nintendo_ds)
                    imageView.visibility = View.VISIBLE
                }
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
                    imageView.setImageResource(R.drawable.xbox_series_x_s)
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
            holder.binding.ivPlatform1,
            holder.binding.ivPlatform2,
            holder.binding.ivPlatform3,
            holder.binding.ivPlatform4,
            holder.binding.ivPlatform5,
            holder.binding.ivPlatform6,
            holder.binding.ivPlatform7,
            holder.binding.ivPlatform8,
            holder.binding.ivPlatform9
        )

        for (i in 0..8) {
            if (game != null) {
                if (game.platforms != null) {
                    if (i < game.platforms?.size!!) {
                        game.platforms?.get(i)?.platform?.id?.let { setPlatform(it, platforms[i]) }
                    }
                }
            }
        }

        if (game?.metacritic != null) {
            when (game.metacritic) {
                in 70..100 -> {
                    holder.binding.tvGameScore.setTextColor(Color.parseColor("#80FEBC"))
                    holder.binding.tvGameScore.setBackgroundResource(R.drawable.rounded_corner_green)
                }
                in 40..69 -> {
                    holder.binding.tvGameScore.setTextColor(Color.parseColor("#FAB753"))
                    holder.binding.tvGameScore.setBackgroundResource(R.drawable.rounded_corner_orange)
                }
                in 0..39 -> {
                    holder.binding.tvGameScore.setTextColor(Color.parseColor("#E33314"))
                    holder.binding.tvGameScore.setBackgroundResource(R.drawable.rounded_corner_red)
                }
            }
        } else {
            holder.binding.tvGameScore.visibility = View.INVISIBLE
        }

        holder.binding.tvGameScore.text = game?.metacritic.toString()

        holder.binding.cvListitemCard.setOnClickListener {
            var img = ""
            var metacritic = 0
            var platformName = ""

            if (game?.metacritic != null) metacritic = game.metacritic
            if (game?.background_image != null) img = game.background_image
            if (game?.platforms?.get(0) != null) platformName = game.platforms!![0].platform.name

            holder.itemView.findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToDetailFragment(
                    game!!.name,
                    img,
                    metacritic,
                    platformName
                )
            )
        }

//      stop das recyclen der views
        holder.setIsRecyclable(false)
    }
}
