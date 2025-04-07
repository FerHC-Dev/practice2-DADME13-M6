package diplomado.practice2.ui.adapters.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import diplomado.practice2.R
import diplomado.practice2.application.PokemonRFApp
import diplomado.practice2.data.PokemonRepository
import diplomado.practice2.databinding.FragmentPokemonDetailBinding
import diplomado.practice2.utils.Constants
import diplomado.practice2.utils.isNetworkAvailable
import kotlinx.coroutines.launch



class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private var pokemonId: String? = null
    private var pokemonName: String? = null

    private lateinit var repository: PokemonRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            pokemonId = args.getString(Constants.ARG_GAMEID)
            pokemonName = args.getString(Constants.ARG_POKENAME)
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

        if(checkNetworkAndLoadData()) {
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
                    message(getString(R.string.error_message, e.message))
                }finally {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }else{
            binding.btnRetry.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PokemonListFragment())
                    .addToBackStack(null)
                    .commit()
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
                    putString(Constants.ARG_GAMEID, id)
                    putString(Constants.ARG_POKENAME, pokemonName)
                }
            }
    }

    private fun checkNetworkAndLoadData(): Boolean {
        if (isNetworkAvailable(requireContext())) {
            // Si hay conexión, cargar los datos
            return true
        } else {
            // Si no hay conexión, mostrar el mensaje de error y el botón de reintentar
            binding.txtConnection.visibility = View.VISIBLE
            binding.btnRetry.visibility = View.VISIBLE
            binding.pbLoading.visibility = View.GONE
            message(getString(R.string.error_connection))
            return false
        }
    }

    private fun message(text : String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT)
            .setTextColor(requireContext().getColor(R.color.white))
            .setBackgroundTint(requireContext().getColor(R.color.black))
            .show()
    }
}