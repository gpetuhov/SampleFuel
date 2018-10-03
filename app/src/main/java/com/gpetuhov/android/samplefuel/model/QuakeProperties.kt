package com.gpetuhov.android.samplefuel.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuakeProperties(
    @Json(name = "place") val location: String,
    @Json(name = "mag") val magnitude: Double
)