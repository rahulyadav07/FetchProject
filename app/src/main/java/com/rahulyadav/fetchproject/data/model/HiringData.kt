package com.rahulyadav.fetchproject.data.model

import com.google.gson.annotations.SerializedName

data class HiringData(
    @SerializedName("id")
    val hiringId :Int? = null,

    @SerializedName("listId")
    val hiringListId:Int? = null,

    @SerializedName("name")
    val hiringName:Int? = null
)
