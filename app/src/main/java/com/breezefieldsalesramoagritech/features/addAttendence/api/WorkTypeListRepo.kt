package com.breezefieldsalesramoagritech.features.addAttendence.api

import com.breezefieldsalesramoagritech.app.Pref
import com.breezefieldsalesramoagritech.features.addAttendence.model.WorkTypeResponseModel
import io.reactivex.Observable

/**
 * Created by Saikat on 31-08-2018.
 */
class WorkTypeListRepo(val apiService: WorkTypeListApi) {
    fun getWorkTypeList(): Observable<WorkTypeResponseModel> {
        return apiService.getWorkType(Pref.user_id!!)
    }
}