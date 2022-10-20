package hector.ruiz.domain.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Street(
    val number: Int?,
    val name: String?
)