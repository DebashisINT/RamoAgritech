package com.breezefieldsalesramoagritech.features.weather.api

import com.breezefieldsalesramoagritech.base.BaseResponse
import com.breezefieldsalesramoagritech.features.task.api.TaskApi
import com.breezefieldsalesramoagritech.features.task.model.AddTaskInputModel
import com.breezefieldsalesramoagritech.features.weather.model.ForeCastAPIResponse
import com.breezefieldsalesramoagritech.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}