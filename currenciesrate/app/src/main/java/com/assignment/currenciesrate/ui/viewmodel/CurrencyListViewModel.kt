package com.assignment.currenciesrate.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.currenciesrate.model.CurrencyCustomModelList
import com.assignment.currenciesrate.network.CurrenciesAPI
import com.assignment.currenciesrate.network.RetrofitHelper
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CurrencyListViewModel : ViewModel() {
    private lateinit var currencyObj: JsonObject
    val gson = Gson()
    val currencyListLiveData = MutableLiveData<LinkedList<CurrencyCustomModelList>>()
    val loading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<String>()

    fun getCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {

            val response =
                RetrofitHelper.getInstance().create(CurrenciesAPI::class.java).getCurrencyList()
                    .let { response ->
                        if (response.isSuccessful) {
                            loading.postValue(false)
                            val responseJsonObjectObject =
                                gson.fromJson(response.body()!!.string(), JsonObject::class.java)
                            if (!responseJsonObjectObject.get("aud").isJsonNull) {
                                currencyObj = gson.fromJson(
                                    responseJsonObjectObject.get("aud").toString(),
                                    JsonObject::class.java
                                )
                            } else {
                                onError.postValue("NULL/EMPTY JSON ")
                            }
                            val currencyList = LinkedList<CurrencyCustomModelList>()
                            //Iterrate currency obj to set value to linked list
                            for (i in currencyObj.entrySet()) {
                                currencyList.add(
                                    CurrencyCustomModelList(
                                        i.key,
                                        i.value.asBigDecimal
                                    )
                                )
                            }
                            descendingOrderSort(currencyList)
                            currencyListLiveData.postValue(currencyList)

                            Log.d("_DDD_", currencyList.size.toString())

                        } else {
                            loading.postValue(false)
                            onError.postValue(response.message())
                        }

                    }
        }
    }

    //Bubble sort
    private fun descendingOrderSort(cl: LinkedList<CurrencyCustomModelList>) {
        for (i in 0 until cl.size - 1) {
            for (j in i + 1 until cl.size - 1) {
                //compare currency value
                if (cl[i].currencyValue < (cl[j].currencyValue)) {
                    val temp = cl[j]
                    cl[j] = cl[i]
                    cl[i] = temp
                }// check currency value equal
                else if (cl.get(i).currencyValue == cl.get(j).currencyValue) {
                    //compare currency code
                    if (cl[i].currencyCode < (cl[j].currencyCode)) {
                        val temp = cl[j]
                        cl[j] = cl[i]
                        cl[i] = temp
                    }
                }
            }
        }
    }
}


