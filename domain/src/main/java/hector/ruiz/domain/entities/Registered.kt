package hector.ruiz.domain.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Registered(
    val date: String
)