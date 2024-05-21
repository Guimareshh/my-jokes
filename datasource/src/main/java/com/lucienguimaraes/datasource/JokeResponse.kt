package com.lucienguimaraes.datasource

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class JokeResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "joke") val joke: String?,
    @Json(name = "setup") val setup: String?,
    @Json(name = "delivery") val delivery: String?,
    @Json(name = "type") val type: JokeType,
)

@JsonClass(generateAdapter = false)
internal enum class JokeType {
    single,
    twopart,
}
