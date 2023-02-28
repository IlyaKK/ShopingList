package com.ilya.shopinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(idItem: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun removeShopItem(shopItem: ShopItem)
}