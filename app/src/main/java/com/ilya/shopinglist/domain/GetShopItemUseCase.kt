package com.ilya.shopinglist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    suspend fun getShopItem(idItem: Int): ShopItem {
        return shopListRepository.getShopItem(idItem)
    }
}