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
import com.example.geizgames.data.models.gameResults.Results
import com.example.geizgames.databinding.ListGameitemBinding
import com.example.geizgames.ui.fragments.GameFragmentDirections

class GameAdapter : PagingDataAdapter<Results, GameAdapter.ItemViewHolder>(diffCallback) {

    inner class ItemViewHolder(val binding: ListGameitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListGameitemBinding.inflate(
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
            crossfade(true)
            crossfade(1000)
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
                107 -> {
                    imageView.setImageResource(R.drawable.sega_white)
                    imageView.visibility = View.VISIBLE
                }

                119 -> {
                    imageView.setImageResource(R.drawable.sega_white)
                    imageView.visibility = View.VISIBLE
                }

                28 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                31 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                23 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                22 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                25 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                34 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                46 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                50 -> {
                    imageView.setImageResource(R.drawable.atari_white)
                    imageView.visibility = View.VISIBLE
                }

                74 -> {
                    imageView.setImageResource(R.drawable.sega_white)
                    imageView.visibility = View.VISIBLE
                }

                17 -> {
                    imageView.setImageResource(R.drawable.psp_white)
                    imageView.visibility = View.VISIBLE
                }

                117 -> {
                    imageView.setImageResource(R.drawable.sega_white)
                    imageView.visibility = View.VISIBLE
                }

                15 -> {
                    imageView.setImageResource(R.drawable.playstation_2_white)
                    imageView.visibility = View.VISIBLE
                }
                106 -> {
                    imageView.setImageResource(R.drawable.dreamcast_white)
                    imageView.visibility = View.VISIBLE
                }
                24 -> {
                    imageView.setImageResource(R.drawable.nintendo_game_boy_white)
                    imageView.visibility = View.VISIBLE
                }
                43 -> {
                    imageView.setImageResource(R.drawable.nintendo_game_boy_white)
                    imageView.visibility = View.VISIBLE
                }
                26 -> {
                    imageView.setImageResource(R.drawable.nintendo_game_boy_white)
                    imageView.visibility = View.VISIBLE
                }
                79 -> {
                    imageView.setImageResource(R.drawable.nintendo_white)
                    imageView.visibility = View.VISIBLE
                }
                49 -> {
                    imageView.setImageResource(R.drawable.nintendo_white)
                    imageView.visibility = View.VISIBLE
                }
                105 -> {
                    imageView.setImageResource(R.drawable.nintendo_gamecube_white)
                    imageView.visibility = View.VISIBLE
                }
                10 -> {
                    imageView.setImageResource(R.drawable.nintendo_wiiu_white)
                    imageView.visibility = View.VISIBLE
                }
                83 -> {
                    imageView.setImageResource(R.drawable.nintendo64_white)
                    imageView.visibility = View.VISIBLE
                }

                11 -> {
                    imageView.setImageResource(R.drawable.nintendo_wii_white)
                    imageView.visibility = View.VISIBLE
                }

                8 -> {
                    imageView.setImageResource(R.drawable.nintendo_ds_white)
                    imageView.visibility = View.VISIBLE
                }
                9 -> {
                    imageView.setImageResource(R.drawable.nintendo_ds_white)
                    imageView.visibility = View.VISIBLE
                }
                4 -> {
                    imageView.setImageResource(R.drawable.windows_pc_white)
                    imageView.visibility = View.VISIBLE
                }
                1 -> {
                    imageView.setImageResource(R.drawable.xbox_white)
                    imageView.visibility = View.VISIBLE
                }
                80 -> {
                    imageView.setImageResource(R.drawable.xbox_white)
                    imageView.visibility = View.VISIBLE
                }
                186 -> {
                    imageView.setImageResource(R.drawable.xbox_series_x_s_white)
                    imageView.visibility = View.VISIBLE
                }
                16 -> {
                    imageView.setImageResource(R.drawable.ps3_white)
                    imageView.visibility = View.VISIBLE
                }
                18 -> {
                    imageView.setImageResource(R.drawable.ps4_white)
                    imageView.visibility = View.VISIBLE
                }
                187 -> {
                    imageView.setImageResource(R.drawable.sony_playstation_white)
                    imageView.visibility = View.VISIBLE
                }
                19 -> {
                    imageView.setImageResource(R.drawable.psvita_white)
                    imageView.visibility = View.VISIBLE
                }
                7 -> {
                    imageView.setImageResource(R.drawable.nintendo_switch_white)
                    imageView.visibility = View.VISIBLE
                }
                5 -> {
                    imageView.setImageResource(R.drawable.mac_os_white)
                    imageView.visibility = View.VISIBLE
                }
                21 -> {
                    imageView.setImageResource(R.drawable.android_os_white)
                    imageView.visibility = View.VISIBLE
                }
                3 -> {
                    imageView.setImageResource(R.drawable.app_ios_white)
                    imageView.visibility = View.VISIBLE
                }
                6 -> {
                    imageView.setImageResource(R.drawable.linux_os_white)
                    imageView.visibility = View.VISIBLE
                }
                14 -> {
                    imageView.setImageResource(R.drawable.xbox360_white)
                    imageView.visibility = View.VISIBLE
                }
                171 -> {
                    imageView.setImageResource(R.drawable.web_white)
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
                    holder.binding.tvGameScore.setTextColor(Color.parseColor("#00BC51"))
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

        val platformArray: Array<String> = arrayOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        val genreArray: Array<String> = arrayOf("", "", "", "", "", "", "", "", "", "","","","")
        var img = ""
        var metacritic = 0

        for (i in 0..12) {
            if (i < game?.genres?.size!! - 1) {
                genreArray[i] = game.genres[i].name
            }
        }
        for (i in 0..14) {
            if (game != null && i < game.platforms?.size!! - 1) {
                platformArray[i] = game.platforms!![i].platform.name
            }
        }

        if (game?.metacritic != null) metacritic = game.metacritic
        if (game?.background_image != null) img = game.background_image

        holder.binding.cvListitemCard.setOnClickListener {
            holder.itemView.findNavController().navigate(
                GameFragmentDirections.actionGameFragmentToDetailFragment(
                    game!!.name,
                    img,
                    metacritic,
                    platformArray,
                    genreArray,
                    game.released!!,
                    game.slug,
                    position
                )
            )
        }

//      stop das recyclen der views
        holder.setIsRecyclable(false)
    }
}
