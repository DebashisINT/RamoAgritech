package com.breezefieldsalesramoagritech.features.stockAddCurrentStock.api

import com.breezefieldsalesramoagritech.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezefieldsalesramoagritech.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}