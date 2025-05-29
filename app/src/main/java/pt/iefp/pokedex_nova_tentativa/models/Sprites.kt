package pt.iefp.pokedex_nova_tentativa.models

import com.google.gson.annotations.SerializedName

class Sprites(
    val other: Other
)

class Other(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld,

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

data class DreamWorld(
    @SerializedName("front_default")
    val frontDefault: String
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String
)
