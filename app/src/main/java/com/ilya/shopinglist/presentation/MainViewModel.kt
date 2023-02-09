package com.ilya.shopinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ilya.shopinglist.data.ShopListRepositoryImpl
import com.ilya.shopinglist.domain.EditShopItemUseCase
import com.ilya.shopinglist.domain.GetShopListUseCase
import com.ilya.shopinglist.domain.RemoveShopItemUseCase
import com.ilya.shopinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun changeEnabledItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
    }
}