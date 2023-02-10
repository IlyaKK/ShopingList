package com.ilya.shopinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilya.shopinglist.domain.ShopItem
import com.ilya.shopinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = sortedSetOf<ShopItem>({ p0, p1 -> p0.id.compareTo(p1.id) })

    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    private var autoIncrementId = 0

    init {
        for (i in 0..10) {
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(idItem: Int): ShopItem {
        return shopList.find {
            it.id == idItem
        } ?: throw RuntimeException("Element with id $idItem not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun removeShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    private fun updateList(){
        shopListLiveData.value = shopList.toList()
    }
}