package com.kundan.demoapp.model


import com.google.gson.annotations.SerializedName

data class ImageResponseItem(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
)