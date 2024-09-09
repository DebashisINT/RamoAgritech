package com.breezefieldsalesramoagritech.features.photoReg.adapter

import com.breezefieldsalesramoagritech.features.photoReg.model.UserListResponseModel

interface PhotoAttendanceListner {
    fun getUserInfoOnLick(obj: UserListResponseModel)
    fun getUserInfoAttendReportOnLick(obj: UserListResponseModel)
}