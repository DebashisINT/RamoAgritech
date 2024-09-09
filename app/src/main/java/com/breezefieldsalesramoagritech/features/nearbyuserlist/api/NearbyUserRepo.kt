package com.breezefieldsalesramoagritech.features.nearbyuserlist.api

import com.breezefieldsalesramoagritech.app.Pref
import com.breezefieldsalesramoagritech.features.nearbyuserlist.model.NearbyUserResponseModel
import com.breezefieldsalesramoagritech.features.newcollection.model.NewCollectionListResponseModel
import com.breezefieldsalesramoagritech.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}