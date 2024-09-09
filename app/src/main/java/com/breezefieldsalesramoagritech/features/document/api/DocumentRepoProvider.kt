package com.breezefieldsalesramoagritech.features.document.api

import com.breezefieldsalesramoagritech.features.dymanicSection.api.DynamicApi
import com.breezefieldsalesramoagritech.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}