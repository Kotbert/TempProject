package com.example.tempproject.bininfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "alpha2") val alpha2: String = "Данные не пришли",
    @Json(name = "currency") val currency: String = "Данные не пришли",
    @Json(name = "emoji") val emoji: String = "Данные не пришли",
    @Json(name = "latitude") val latitude: Int = 0,
    @Json(name = "longitude") val longitude: Int = 0,
    @Json(name = "name") val name: String = "Данные не пришли",
    @Json(name = "numeric") val numeric: String = "Данные не пришли"
)