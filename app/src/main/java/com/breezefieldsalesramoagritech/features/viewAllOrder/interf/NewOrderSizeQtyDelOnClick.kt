package com.breezefieldsalesramoagritech.features.viewAllOrder.interf

import com.breezefieldsalesramoagritech.app.domain.NewOrderGenderEntity
import com.breezefieldsalesramoagritech.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}