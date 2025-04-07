package diplomado.practice2.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)
