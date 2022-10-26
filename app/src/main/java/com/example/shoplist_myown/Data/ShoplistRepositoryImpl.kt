package com.example.shoplist_myown.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist_myown.Domain.Shopitem
import com.example.shoplist_myown.Domain.ShoplistRepositiry

object ShoplistRepositoryImpl: ShoplistRepositiry {
    val shoplist = mutableListOf<Shopitem>()
    var shoplist_LD = MutableLiveData<List<Shopitem>>()
    var autoincrement = 0

    override fun addShopitem(item: Shopitem) {
        item.id = autoincrement++
        shoplist.add(item)
        updateShoplist()
    }

    override fun deleteShopitem(item: Shopitem) {
        shoplist.remove(item)
        updateShoplist()
    }

    override fun editShopitem(item: Shopitem) {
        val olditem = getShopitemByID(item.id)
        shoplist.remove(olditem)
        addShopitem(item)
    }

    override fun getShopitemByID(id: Int): Shopitem {
        return shoplist.find { it.id == id } ?: throw RuntimeException("Нет такого ID")
    }

    override fun getShoplist(): LiveData<List<Shopitem>> {
        return shoplist_LD
    }

    fun updateShoplist() {
        shoplist_LD.value = shoplist.toList()
    }
}