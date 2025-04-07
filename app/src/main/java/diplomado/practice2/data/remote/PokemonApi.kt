package diplomado.practice2.data.remote

import diplomado.practice2.data.remote.model.PokemonDetailDto
import diplomado.practice2.data.remote.model.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemons/pokemons_list")
    suspend fun getPokemons(): List<PokemonDto>

    @GET("pokemons/pokemon_detail/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: String? = null
    ): PokemonDetailDto

}