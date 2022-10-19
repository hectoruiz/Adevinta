package hector.ruiz.domain.entities

data class User(
    val name: Name?,
    val email: String?,
    val picture: Picture?,
    val phone: String?,
    val gender: String?,
    val location: Location?,
    val registered: Registered?
)