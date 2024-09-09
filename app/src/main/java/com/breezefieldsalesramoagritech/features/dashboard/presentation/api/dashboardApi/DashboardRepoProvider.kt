package com.breezefieldsalesramoagritech.features.dashboard.presentation.api.dashboardApi

import com.breezefieldsalesramoagritech.features.login.api.LoginApi
import com.breezefieldsalesramoagritech.features.login.api.LoginRepository

/**
 * Created by Saikat on 26-Jun-20.
 */
object DashboardRepoProvider {
    fun provideDashboardImgRepository(): DashboardRepo {
        return DashboardRepo(DashboardApi.img())
    }

    fun provideDashboardRepository(): DashboardRepo {
        return DashboardRepo(DashboardApi.create())
    }
}