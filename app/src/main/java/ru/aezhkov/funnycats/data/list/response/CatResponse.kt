package ru.aezhkov.funnycats.data.list.response

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String
)