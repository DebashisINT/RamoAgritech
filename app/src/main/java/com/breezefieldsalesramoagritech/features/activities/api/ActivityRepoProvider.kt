package com.breezefieldsalesramoagritech.features.activities.api

import com.breezefieldsalesramoagritech.features.member.api.TeamApi
import com.breezefieldsalesramoagritech.features.member.api.TeamRepo

object ActivityRepoProvider {
    fun activityRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.create())
    }

    fun activityImageRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.createImage())
    }
}