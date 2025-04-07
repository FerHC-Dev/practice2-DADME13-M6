package diplomado.practice2.data

import diplomado.practice2.data.remote.PokemonApi
import diplomado.practice2.data.remote.model.PokemonDetailDto
import diplomado.practice2.data.remote.model.PokemonDto
import retrofit2.Retrofit

class PokemonRepository(
    private val retrofit: Retrofit
) {
    private val pokemonApi = retrofit.create(PokemonApi::class.java)

    suspend fun getPokemons(): List<PokemonDto> = pokemonApi.getPokemons()

    suspend fun getPokemonDetail(id: String?): PokemonDetailDto
        = pokemonApi.getPokemonDetail(id)

}