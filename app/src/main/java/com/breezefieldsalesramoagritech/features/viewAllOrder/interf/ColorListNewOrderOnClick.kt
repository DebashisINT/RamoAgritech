package com.breezefieldsalesramoagritech.features.viewAllOrder.interf

import com.breezefieldsalesramoagritech.app.domain.NewOrderColorEntity
import com.breezefieldsalesramoagritech.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}