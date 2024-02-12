package ru.tinkoff.lab.presentation.favouriteFilms.filmList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.tinkoff.lab.App
import ru.tinkoff.lab.R
import ru.tinkoff.lab.databinding.FavouriteListBinding
import ru.tinkoff.lab.presentation.ViewModelFactory
import ru.tinkoff.lab.presentation.favouriteFilms.filmList.adapter.FavouriteFilmAdapter
import javax.inject.Inject

class FavouriteListFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).appComponent
    }
    private var _binding: FavouriteListBinding? = null
    private val binding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = FavouriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.favouriteButton.setOnClickListener {
            navController.navigateUp()
            navController.navigate(R.id.favouriteListFragment)
        }
        binding.popularButton.setOnClickListener {
            navController.navigateUp()
            navController.navigate(R.id.filmList)
        }
        val filmsRecyclerView = binding.previewFilmRecyclerView
        filmsRecyclerView.layoutManager = LinearLayoutManager(context)


        val adapter =
            FavouriteFilmAdapter(
                { previewFilm ->
                    findNavController().navigate(
                        FavouriteListFragmentDirections.actionFavouriteListFragmentToFavouriteDetailsFragment(
                            previewFilm.filmId
                        )
                    )
                },
                { previewFilm ->
                    viewModel.deleteFilm(previewFilm.filmId)
                })

        filmsRecyclerView.adapter = adapter


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loadFilmsList
                    .collect { filmList ->
                        adapter.submitList(filmList)
                    }

            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}