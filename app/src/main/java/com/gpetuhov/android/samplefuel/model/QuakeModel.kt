package com.gpetuhov.android.samplefuel.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class QuakeModel(
    @Json(name = "properties") val quakeProperties: QuakeProperties
)