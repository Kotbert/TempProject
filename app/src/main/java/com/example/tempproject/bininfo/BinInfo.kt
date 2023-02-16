package com.example.tempproject.bininfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BinInfo(
    @Json(name = "bank") val bank: Bank = Bank(),
    @Json(name = "brand") val brand: String = "Данные не пришли",
    @Json(name = "country") val country: Country = Country(),
    @Json(name = "number") val number: Number = Number(),
    @Json(name = "prepaid") val prepaid: Boolean = false,
    @Json(name = "scheme") val scheme: String = "Данные не пришли",
    @Json(name = "type") val type: String = "Данные не пришли"
)