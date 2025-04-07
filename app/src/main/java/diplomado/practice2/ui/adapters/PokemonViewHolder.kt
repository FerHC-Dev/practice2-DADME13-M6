package diplomado.practice2.ui.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import diplomado.practice2.R
import diplomado.practice2.data.remote.model.PokemonDto
import diplomado.practice2.databinding.PokemonElementBinding

class PokemonViewHolder(
    private val binding: PokemonElementBinding,
    private val context: Context
): RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: PokemonDto){
        binding.apply {
            tvTitle.text = context.resources.getString(R.string.element_title, pokemon.id)
            tvName.text = pokemon.name
            tvRegion.text = pokemon.region
            Glide.with(binding.root)
                .load(pokemon.thumbnail)
                .into(binding.ivThumbnail)
        }

    }

}