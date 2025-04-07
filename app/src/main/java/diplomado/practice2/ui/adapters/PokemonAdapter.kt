package diplomado.practice2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import diplomado.practice2.data.remote.model.PokemonDto
import diplomado.practice2.databinding.PokemonElementBinding

class PokemonAdapter(
    private val pokemons: List<PokemonDto>,
    private val onPokemonClicked: (PokemonDto) -> Unit
) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
        holder.itemView.setOnClickListener {
            onPokemonClicked(pokemon)
        }

    }

}