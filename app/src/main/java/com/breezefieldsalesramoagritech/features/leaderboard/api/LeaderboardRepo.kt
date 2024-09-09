package com.breezefieldsalesramoagritech.features.leaderboard.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.breezefieldsalesramoagritech.app.FileUtils
import com.breezefieldsalesramoagritech.app.Pref
import com.breezefieldsalesramoagritech.base.BaseResponse
import com.breezefieldsalesramoagritech.features.addshop.model.AddLogReqData
import com.breezefieldsalesramoagritech.features.addshop.model.AddShopRequestData
import com.breezefieldsalesramoagritech.features.addshop.model.AddShopResponse
import com.breezefieldsalesramoagritech.features.addshop.model.LogFileResponse
import com.breezefieldsalesramoagritech.features.addshop.model.UpdateAddrReq
import com.breezefieldsalesramoagritech.features.contacts.CallHisDtls
import com.breezefieldsalesramoagritech.features.contacts.CompanyReqData
import com.breezefieldsalesramoagritech.features.contacts.ContactMasterRes
import com.breezefieldsalesramoagritech.features.contacts.SourceMasterRes
import com.breezefieldsalesramoagritech.features.contacts.StageMasterRes
import com.breezefieldsalesramoagritech.features.contacts.StatusMasterRes
import com.breezefieldsalesramoagritech.features.contacts.TypeMasterRes
import com.breezefieldsalesramoagritech.features.dashboard.presentation.DashboardActivity
import com.breezefieldsalesramoagritech.features.login.model.WhatsappApiData
import com.breezefieldsalesramoagritech.features.login.model.WhatsappApiFetchData
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Puja on 10-10-2024.
 */
class LeaderboardRepo(val apiService: LeaderboardApi) {

    fun branchlist(session_token: String): Observable<LeaderboardBranchData> {
        return apiService.branchList(session_token)
    }
    fun ownDatalist(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOwnData> {
        return apiService.ownDatalist(user_id,activitybased,branchwise,flag)
    }
    fun overAllAPI(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOverAllData> {
        return apiService.overAllDatalist(user_id,activitybased,branchwise,flag)
    }
}