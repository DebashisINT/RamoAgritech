package com.breezefieldsalesramoagritech.features.newcollectionreport

import com.breezefieldsalesramoagritech.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}