package com.breezefieldsalesramoagritech.features.survey.api

import com.breezefieldsalesramoagritech.features.photoReg.api.GetUserListPhotoRegApi
import com.breezefieldsalesramoagritech.features.photoReg.api.GetUserListPhotoRegRepository

object SurveyDataProvider{

    fun provideSurveyQ(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.create())
    }

    fun provideSurveyQMultiP(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.createImage())
    }
}