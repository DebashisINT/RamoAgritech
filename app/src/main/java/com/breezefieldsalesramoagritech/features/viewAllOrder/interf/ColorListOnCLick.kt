package com.breezefieldsalesramoagritech.features.viewAllOrder.interf

import com.breezefieldsalesramoagritech.app.domain.NewOrderGenderEntity
import com.breezefieldsalesramoagritech.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}