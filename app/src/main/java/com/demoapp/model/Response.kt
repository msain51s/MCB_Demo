package com.demoapp.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName

@SuppressLint("ParcelCreator")
data class Response(
    @SerializedName("result")
    val result: Int
)