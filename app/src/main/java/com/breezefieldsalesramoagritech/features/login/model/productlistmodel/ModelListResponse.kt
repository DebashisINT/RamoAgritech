package com.breezefieldsalesramoagritech.features.login.model.productlistmodel

import com.breezefieldsalesramoagritech.app.domain.ModelEntity
import com.breezefieldsalesramoagritech.app.domain.ProductListEntity
import com.breezefieldsalesramoagritech.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}