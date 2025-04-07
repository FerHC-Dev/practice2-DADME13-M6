package diplomado.practice2.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailDto(
    @SerializedName("type")
    var type: String,
    @SerializedName("type2")
    var type2: String,
    @SerializedName("abilitie")
    var abilitie: String,
    @SerializedName("Species")
    var species: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("height")
    var height: String,
    @SerializedName("weight")
    var weight: String,
)
