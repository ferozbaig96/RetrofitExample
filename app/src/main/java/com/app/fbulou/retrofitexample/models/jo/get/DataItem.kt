package com.app.fbulou.retrofitexample.models.jo.get

import com.google.gson.annotations.SerializedName

data class DataItem(

        @field:SerializedName("last_name")
        val lastName: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("avatar")
        val avatar: String? = null,

        @field:SerializedName("first_name")
        val firstName: String? = null
)