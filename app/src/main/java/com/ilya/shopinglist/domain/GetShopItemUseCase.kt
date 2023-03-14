package com.ilya.shopinglist.domain

import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {

    suspend fun getShopItem(idItem: Int): ShopItem {
        return shopListRepository.getShopItem(idItem)
    }
}