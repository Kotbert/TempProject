package com.example.tempproject.bininfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
    @Json(name = "city") val city: String = "Данные не пришли",
    @Json(name = "name") val name: String = "Данные не пришли",
    @Json(name = "phone") val phone: String = "Данные не пришли",
    @Json(name = "url") val url: String = "Данные не пришли"
)