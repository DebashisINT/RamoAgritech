package com.breezefieldsalesramoagritech.features.weather.api

import com.breezefieldsalesramoagritech.features.task.api.TaskApi
import com.breezefieldsalesramoagritech.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}