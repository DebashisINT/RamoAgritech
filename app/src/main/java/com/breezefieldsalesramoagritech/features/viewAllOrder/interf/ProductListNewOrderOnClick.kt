package com.breezefieldsalesramoagritech.features.viewAllOrder.interf

import com.breezefieldsalesramoagritech.app.domain.NewOrderGenderEntity
import com.breezefieldsalesramoagritech.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}