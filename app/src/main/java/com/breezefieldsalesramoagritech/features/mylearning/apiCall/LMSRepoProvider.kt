package com.breezefieldsalesramoagritech.features.mylearning.apiCall

import com.breezefieldsalesramoagritech.features.login.api.opportunity.OpportunityListApi
import com.breezefieldsalesramoagritech.features.login.api.opportunity.OpportunityListRepo

object LMSRepoProvider {
    fun getTopicList(): LMSRepo {
        return LMSRepo(LMSApi.create())
    }
}