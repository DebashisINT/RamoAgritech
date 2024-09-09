package com.breezefieldsalesramoagritech.features.viewAllOrder.api.addorder

import com.breezefieldsalesramoagritech.features.timesheet.api.TimeSheetApi
import com.breezefieldsalesramoagritech.features.timesheet.api.TimeSheetRepo

/**
 * Created by Saikat on 01-10-2018.
 */
object AddOrderRepoProvider {
    fun provideAddOrderRepository(): AddOrderRepo {
        return AddOrderRepo(AddOrderApi.create())
    }

    fun provideAddOrderImageRepository(): AddOrderRepo {
        return AddOrderRepo(AddOrderApi.createImage())
    }
}