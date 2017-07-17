package com.app.fbulou.retrofitexample.models.jo.get

import com.google.gson.annotations.SerializedName

data class SamplePojo(

        @field:SerializedName("per_page")
        val perPage: Int? = null,

        @field:SerializedName("total")
        val total: Int? = null,

        @field:SerializedName("data")
        val data: List<DataItem?>? = null,

        @field:SerializedName("page")
        val page: String? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null
)