package com.breezefieldsalesramoagritech.features.photoReg.present

import com.breezefieldsalesramoagritech.app.domain.ProspectEntity
import com.breezefieldsalesramoagritech.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}