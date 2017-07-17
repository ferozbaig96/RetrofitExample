package com.app.fbulou.retrofitexample.models.ja

import com.google.gson.annotations.SerializedName

data class SampleArrayResponse(

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("title")
        val title: String? = null,

        @field:SerializedName("body")
        val body: String? = null,

        @field:SerializedName("userId")
        val userId: Int? = null
)