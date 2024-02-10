package ru.tinkoff.lab.presentation.films.filmList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
import ru.tinkoff.lab.databinding.FilmsListBinding
import ru.tinkoff.lab.domain.state.FilmListState
import ru.tinkoff.lab.presentation.ViewModelFactory
import ru.tinkoff.lab.presentation.films.filmList.adapter.PreviewFilmAdapter
import javax.inject.Inject

class FilmListFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).appComponent
    }
    private var _binding: FilmsListBinding? = null
    private val binding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FilmListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = FilmsListBinding.inflate(inflater, container, false)
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
            PreviewFilmAdapter(
                { previewFilm ->
                    findNavController().navigate(
                        FilmListFragmentDirections.actionFilmListToDetailsFilmFragment(
                            previewFilm.filmId
                        )
                    )
                },
                { previewFilm -> viewModel.addFavourite(previewFilm) })

        filmsRecyclerView.adapter = adapter
        binding.refreshButton.setOnClickListener {
            viewModel.refreshFilmList()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loadFilmsList
                    .collect { filmListState ->
                        when (filmListState) {
                            is FilmListState.Loading -> {
                                binding.emptyView.visibility = GONE
                                binding.progressBarLoading.visibility = VISIBLE
                            }

                            is FilmListState.Success -> {
                                binding.emptyView.visibility = GONE
                                binding.progressBarLoading.visibility = GONE
                                binding.preview.visibility = VISIBLE
                                adapter.submitList(filmListState.filmsList)
                            }

                            is FilmListState.Error -> {
                                binding.preview.visibility = GONE
                                binding.emptyView.visibility = VISIBLE
                            }
                        }

                    }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}