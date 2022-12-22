package com.assignment.currenciesrate.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CurrenciesAPI {
    @GET("/gh/fawazahmed0/currency-api@1/latest/currencies/aud.json")
    suspend fun getCurrencyList(): Response<ResponseBody>
}