package com.breezefieldsalesramoagritech.features.location.api

import com.breezefieldsalesramoagritech.app.Pref
import com.breezefieldsalesramoagritech.base.BaseResponse
import com.breezefieldsalesramoagritech.features.location.model.AppInfoInputModel
import com.breezefieldsalesramoagritech.features.location.model.AppInfoResponseModel
import com.breezefieldsalesramoagritech.features.location.model.GpsNetInputModel
import com.breezefieldsalesramoagritech.features.location.model.ShopDurationRequest
import com.breezefieldsalesramoagritech.features.location.shopdurationapi.ShopDurationApi
import io.reactivex.Observable

/**
 * Created by Saikat on 17-Aug-20.
 */
class LocationRepo(val apiService: LocationApi) {
    fun appInfo(appInfo: AppInfoInputModel?): Observable<BaseResponse> {
        return apiService.submitAppInfo(appInfo)
    }

    fun getAppInfo(): Observable<AppInfoResponseModel> {
        return apiService.getAppInfo(Pref.session_token!!, Pref.user_id!!)
    }

    fun gpsNetInfo(appInfo: GpsNetInputModel?): Observable<BaseResponse> {
        return apiService.submitGpsNetInfo(appInfo)
    }
}