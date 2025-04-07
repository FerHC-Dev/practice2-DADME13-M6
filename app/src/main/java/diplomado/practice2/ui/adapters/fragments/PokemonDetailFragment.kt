package diplomado.practice2.ui.adapters.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import diplomado.practice2.R
import diplomado.practice2.application.PokemonRFApp
import diplomado.practice2.data.PokemonRepository
import diplomado.practice2.databinding.FragmentPokemonDetailBinding
import kotlinx.coroutines.launch

private const val ARG_GAMEID = "id"
private const val ARG_POKENAME = "name"

class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private var pokemonId: String? = null
    private var pokemonName: String? = null

    private lateinit var repository: PokemonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            pokemonId = args.getString(ARG_GAMEID)
            pokemonName = args.getString(ARG_POKENAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as PokemonRFApp).repository

        lifecycleScope.launch {
            try{
                val pokemonDetail = repository.getPokemonDetail(pokemonId)

                binding.apply {
                    tvTitle.text = pokemonName

                    tvType1.text = getString(R.string.detail_type,pokemonDetail.type)
                    tvType2.text = getString(R.string.detail_type2,pokemonDetail.type2)
                    tvHeight.text = getString(R.string.detail_Height,pokemonDetail.height)
                    tvWeight.text = getString(R.string.detail_weight,pokemonDetail.weight)
                    tvAbilities.text = getString(R.string.detail_abilities,pokemonDetail.abilitie)
                    tvSpecies.text = getString(R.string.detail_species,pokemonDetail.species)
                    Glide.with(requireContext())
                        .load(pokemonDetail.image)
                        .into(ivImage)
                }

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                binding.pbLoading.visibility = View.GONE
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String, pokemonName: String) =
            PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_GAMEID, id)
                    putString(ARG_POKENAME, pokemonName)
                }
            }
    }
}