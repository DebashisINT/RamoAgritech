package com.breezefieldsalesramoagritech.features.viewAllOrder.model

import com.breezefieldsalesramoagritech.app.domain.NewOrderColorEntity
import com.breezefieldsalesramoagritech.app.domain.NewOrderGenderEntity
import com.breezefieldsalesramoagritech.app.domain.NewOrderProductEntity
import com.breezefieldsalesramoagritech.app.domain.NewOrderSizeEntity
import com.breezefieldsalesramoagritech.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

