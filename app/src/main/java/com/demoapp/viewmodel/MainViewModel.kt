package com.demoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demoapp.client.APIClient
import com.demoapp.model.Response
import retrofit2.Call
import retrofit2.Callback

/**
 * Created by Manoj Sain on 2019-12-19.
 */
class MainViewModel:ViewModel() {

    private val client = APIClient()
    private var responseMutableLiveData: MutableLiveData<Response> = MutableLiveData()
    private var lastSeatNoGettingTreatLiveData: MutableLiveData<Int> = MutableLiveData()
    private var resultMapLiveData: MutableLiveData<HashMap<Int,Int>> = MutableLiveData()

    fun getResponse(): LiveData<Response> {
        return responseMutableLiveData
    }

    fun getLastSeatNoGettingTreat(): LiveData<Int> {
        return lastSeatNoGettingTreatLiveData
    }

    fun getResultMap(): LiveData<HashMap<Int,Int>> {
        return resultMapLiveData
    }

    fun doCalculation(firstValue: String,secondValue:String){

        var requestParams= ArrayList<Int>()
        requestParams.add(firstValue.toInt())
        requestParams.add(secondValue.toInt())

        client.getAPIInterface().getResult(client.code,
            requestParams).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                responseMutableLiveData.value=response.body()
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {

            }
        })
    }

    fun getResults(treats:Int,seats:Int, draw:Int){

        var hashMap=HashMap<Int,Int>()

        var treatArr= IntArray(treats)
        var j=0
        var draw1=draw

        /**
         * creating treats array
         */
        while (j<treats){

            if(draw1>seats){
                draw1=1
            }

            treatArr[j]=draw1

            draw1++

            j++
        }

        /**
         * getting occurence of seats
         */
        var i=0
        while(i<treatArr.size){
            if(hashMap.containsKey(treatArr[i])){
                var value:Int = hashMap[treatArr[i]] as Int
                hashMap[treatArr[i]]= value+1
            }else{
                hashMap[treatArr[i]]=1
            }

            i++
        }
         resultMapLiveData.value=hashMap

        /**
         * getting last seat number which is getting treat
         */
        var lastGettingTreatSeatNo=treats%seats + draw -1
        if(lastGettingTreatSeatNo>seats)
            lastGettingTreatSeatNo -= seats

        lastSeatNoGettingTreatLiveData.value=lastGettingTreatSeatNo
    }
}