package diplomado.practice2.ui.adapters.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import diplomado.practice2.R
import diplomado.practice2.application.PokemonRFApp
import diplomado.practice2.data.PokemonRepository
import diplomado.practice2.databinding.FragmentPokemonListBinding
import diplomado.practice2.ui.adapters.PokemonAdapter
import diplomado.practice2.utils.isNetworkAvailable
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

        if(checkNetworkAndLoadData()) {
            repository = (requireActivity().application as PokemonRFApp).repository

            lifecycleScope.launch {
                try {
                    val pokemons = repository.getPokemons()

                    binding.rvPokemons.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = PokemonAdapter(pokemons) { pokemon ->
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

                } catch (e: Exception) {
                    e.printStackTrace()
                    message(getString(R.string.error_loading))
                } finally {
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