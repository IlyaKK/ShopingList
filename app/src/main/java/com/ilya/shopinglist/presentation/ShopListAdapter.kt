package com.ilya.shopinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.ilya.shopinglist.R
import com.ilya.shopinglist.databinding.ItemShopDisableBinding
import com.ilya.shopinglist.databinding.ItemShopEnableBinding
import com.ilya.shopinglist.domain.ShopItem

class ShopListAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffUtilCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val idLayout = when (viewType) {
            ENABLE_STATE -> R.layout.item_shop_enable
            DISABLE_STATE -> R.layout.item_shop_disable
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            idLayout,
            parent,
            false
        )

        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = holder.binding

        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ItemShopDisableBinding -> {
                binding.shopItem = shopItem
            }
            is ItemShopEnableBinding -> {
                binding.shopItem = shopItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            ENABLE_STATE
        } else {
            DISABLE_STATE
        }
    }

    companion object {
        const val ENABLE_STATE = 0
        const val DISABLE_STATE = 1

        const val MAX_POOL_SIZE = 20
    }
}