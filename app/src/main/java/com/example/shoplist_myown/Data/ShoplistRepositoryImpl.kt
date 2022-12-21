package com.example.shoplist_myown.Data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplist_myown.Domain.Shopitem
import com.example.shoplist_myown.Domain.ShoplistRepositiry
import kotlin.random.Random

class ShoplistRepositoryImpl(application: Application): ShoplistRepositiry {

    val shoplistDAO = AppDatabase.getInstance(application).shopListDao()
    val mapper = Mapper()

    override fun addShopitem(item: Shopitem) {
        shoplistDAO.addShopItemDb(mapper.mapEntityToShopitemDB(item))
    }

    override fun deleteShopitem(item: Shopitem) {
        shoplistDAO.deleteShopItemDb(item.id)
    }

    override fun editShopitem(item: Shopitem) {
        shoplistDAO.addShopItemDb(mapper.mapEntityToShopitemDB(item))
    }

    override fun getShopitemByID(id: Int): Shopitem {
        return mapper.mapShopitemDBtoEntity(shoplistDAO.getShopItemByID(id))
    }

    override fun getShoplist(): LiveData<List<Shopitem>> {
        return mapper.mapListDBToListEntity()
    }
}