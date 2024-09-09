package com.breezefieldsalesramoagritech.features.viewAllOrder.orderOptimized

import com.breezefieldsalesramoagritech.app.domain.ProductOnlineRateTempEntity
import com.breezefieldsalesramoagritech.base.BaseResponse
import com.breezefieldsalesramoagritech.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}