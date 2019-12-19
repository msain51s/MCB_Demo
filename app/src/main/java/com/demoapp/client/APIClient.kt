package com.demoapp.client
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Manoj Sain on 2019-12-19.
 */
class APIClient {

    val code="aKHx1nNzQpgy9WXzNOJnJR4a/KpX05PTepvQeCvoq7CbanG84Ng81A=="
    private  val BASE_URL="https://c8r.azurewebsites.net"
    private var retrofit:Retrofit
    private var apiInterface: APIInterface

    init {
        retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface=retrofit.create(APIInterface::class.java)
    }

    fun getAPIInterface(): APIInterface {
        return apiInterface
    }

}