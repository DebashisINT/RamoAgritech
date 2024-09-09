package com.breezefieldsalesramoagritech.features.location.api

import com.breezefieldsalesramoagritech.features.location.shopdurationapi.ShopDurationApi
import com.breezefieldsalesramoagritech.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}