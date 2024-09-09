package com.breezefieldsalesramoagritech.features.viewAllOrder.interf

import com.breezefieldsalesramoagritech.app.domain.NewOrderProductEntity
import com.breezefieldsalesramoagritech.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}