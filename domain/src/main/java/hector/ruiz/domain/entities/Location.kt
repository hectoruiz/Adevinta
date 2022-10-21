package hector.ruiz.domain.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val street: Street?,
    val city: String?,
    val state: String?
)