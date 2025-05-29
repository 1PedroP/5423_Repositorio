import com.google.gson.annotations.SerializedName
import pt.iefp.pokedex_nova_tentativa.models.OfficialArtwork

data class Other( // Adiciona "data" aqui
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld,

    @SerializedName("official_artwork")
    val officialArtwork: OfficialArtwork
)