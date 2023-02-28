package com.ilya.shopinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ilya.shopinglist.data.ShopListRepositoryImpl
import com.ilya.shopinglist.domain.EditShopItemUseCase
import com.ilya.shopinglist.domain.GetShopListUseCase
import com.ilya.shopinglist.domain.RemoveShopItemUseCase
import com.ilya.shopinglist.domain.ShopItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

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