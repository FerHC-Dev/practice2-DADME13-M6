package diplomado.practice2.application

import android.app.Application
import diplomado.practice2.data.PokemonRepository
import diplomado.practice2.data.remote.RetrofitHelper

class PokemonRFApp: Application() {

    private val retrofit by lazy {
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy {
        PokemonRepository(retrofit)
    }
}