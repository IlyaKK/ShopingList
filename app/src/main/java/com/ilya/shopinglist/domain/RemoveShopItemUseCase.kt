package com.ilya.shopinglist.domain

import javax.inject.Inject

class RemoveShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {

    suspend fun removeShopItem(shopItem: ShopItem) {
        shopListRepository.removeShopItem(shopItem)
    }
}