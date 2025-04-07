package diplomado.practice2.ui.adapters.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import diplomado.practice2.R
import diplomado.practice2.application.PokemonRFApp
import diplomado.practice2.data.PokemonRepository
import diplomado.practice2.data.remote.model.PokemonDto
import diplomado.practice2.databinding.FragmentPokemonListBinding
import diplomado.practice2.ui.adapters.PokemonAdapter
import diplomado.practice2.utils.Constants
import kotlinx.coroutines.launch

class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: PokemonRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as PokemonRFApp).repository

        lifecycleScope.launch {
            try{
                val pokemons = repository.getPokemons()

                binding.rvPokemons.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = PokemonAdapter(pokemons){ pokemon ->
                        pokemon.id?.let { id ->
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    PokemonDetailFragment.newInstance(id, pokemon.name)
                                )
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }

            }catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(requireContext()
                    ,"Error: ${e.message}"
                    , Toast.LENGTH_SHORT)
                    .show()
            }finally {
                binding.pbLoading.visibility = View.GONE
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}