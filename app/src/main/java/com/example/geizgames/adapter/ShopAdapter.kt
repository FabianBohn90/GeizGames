package com.example.geizgames.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.geizgames.R
import com.example.geizgames.data.models.shopResults.Stores
import com.example.geizgames.databinding.ListShopitemBinding

class ShopAdapter(
    private val dataset: List<Stores>,
    private val context: Activity
) : RecyclerView.Adapter<ShopAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListShopitemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListShopitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        val twoD = "%.2f"

        holder.binding.tvShopName.text = item.seller
        holder.binding.tvShopPrice.text = twoD.format(item.price)

        val uri = item.url

        when (item.seller) {
            "Yuplay" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_yuplay)
            "Eneba" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_eneba)
            "Kinguin" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_kinguin)
            "G2Play" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_g2play)
            "HRK Game" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_hrko)
            "MMOGA" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_mmoga)
            "GAMIVO" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_gamivo)
            "Instant Gaming" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_instant_gaming)
            "G2A" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_g2a_com_logo)
            "CDKeys.com" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_cdkeys)
            "Wyrel.com" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_wyrel)
            "Steam" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_steam)
            "Origin" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_origin)
            "GOG.com" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_gog)
            "Humble Store" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_humblestore)
            "Epic Games Store" -> holder.binding.ivStoreIcon.setImageResource(R.drawable.shop_epic_store)
        }

        holder.binding.cvShop.setOnClickListener {
            uri.asUri()?.openInBrowser(context)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}

fun Uri?.openInBrowser(context: Context) {
    this ?: return // Do nothing if uri is null

    val browserIntent = Intent(Intent.ACTION_VIEW, this)
    ContextCompat.startActivity(context, browserIntent, null)
}

fun String?.asUri(): Uri? {
    return try {
        Uri.parse(this)
    } catch (e: Exception) {
        null
    }
}
