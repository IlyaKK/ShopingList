package com.ilya.shopinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilya.shopinglist.R
import com.ilya.shopinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    val list = listOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enable, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = list[position]
        holder.nameTv.text = shopItem.name
        holder.countTv.text = shopItem.count.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameTv: TextView = view.findViewById(R.id.name_item_tv)
        val countTv: TextView = view.findViewById(R.id.count_tv)
    }
}