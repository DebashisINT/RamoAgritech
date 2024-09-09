package com.breezefieldsalesramoagritech.features.lead.api

import com.breezefieldsalesramoagritech.features.NewQuotation.api.GetQuotListRegRepository
import com.breezefieldsalesramoagritech.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}