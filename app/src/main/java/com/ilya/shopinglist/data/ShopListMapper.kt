package com.ilya.shopinglist.data

import com.ilya.shopinglist.domain.ShopItem

class ShopListMapper {
    fun mapEntityToDbModel(shopItem: ShopItem) = ShopItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapDbModelToEntity(dbShopItem: ShopItemDbModel) = ShopItem(
        id = dbShopItem.id,
        name = dbShopItem.name,
        count = dbShopItem.count,
        enabled = dbShopItem.enabled
    )

    fun listDbModelToListEntity(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}