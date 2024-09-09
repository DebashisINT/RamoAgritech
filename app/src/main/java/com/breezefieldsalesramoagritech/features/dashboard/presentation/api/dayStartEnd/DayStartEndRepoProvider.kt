package com.breezefieldsalesramoagritech.features.dashboard.presentation.api.dayStartEnd

import com.breezefieldsalesramoagritech.features.stockCompetetorStock.api.AddCompStockApi
import com.breezefieldsalesramoagritech.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}