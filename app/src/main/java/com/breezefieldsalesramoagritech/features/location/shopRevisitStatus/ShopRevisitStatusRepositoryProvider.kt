package com.breezefieldsalesramoagritech.features.location.shopRevisitStatus

import com.breezefieldsalesramoagritech.features.location.shopdurationapi.ShopDurationApi
import com.breezefieldsalesramoagritech.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}