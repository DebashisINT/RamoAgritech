package com.breezefieldsalesramoagritech.features.login.model.opportunitymodel

import com.breezefieldsalesramoagritech.app.domain.OpportunityStatusEntity
import com.breezefieldsalesramoagritech.app.domain.ProductListEntity
import com.breezefieldsalesramoagritech.base.BaseResponse

/**
 * Created by Puja on 30.05.2024
 */
class OpportunityStatusListResponseModel : BaseResponse() {
    var status_list: ArrayList<OpportunityStatusEntity>? = null
}