package com.example.tempproject.bininfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Number(
    @Json(name = "length") val length: Int = 0,
    @Json(name = "luhn") val luhn: Boolean = false
)
