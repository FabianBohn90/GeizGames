package com.example.geizgames.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
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

        holder.binding.tvShopName.text = item.seller
        holder.binding.tvShopPrice.text = item.price.toString()

        val uri = item.url

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
