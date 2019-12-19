package com.demoapp.client

import com.demoapp.model.Response
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Manoj Sain on 2019-12-19.
 */
interface APIInterface {

    @Headers("Content-Type: application/json")
    @POST("/api/add")
    fun getResult(@Query("code") code: String, @Body params:List<Int>): Call<Response>
}