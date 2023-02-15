package com.ilya.shopinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilya.shopinglist.R

class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val nameTv: TextView = view.findViewById(R.id.name_item_tv)
    val countTv: TextView = view.findViewById(R.id.count_tv)
}