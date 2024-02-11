package ru.tinkoff.lab.presentation.filmList.detailsFilm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.vkclientnews.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import ru.tinkoff.lab.App
import ru.tinkoff.lab.databinding.DetailsFilmItemBinding
import javax.inject.Inject

class DetailsFilmFragment : Fragment() {

    private val args by navArgs<DetailsFilmFragmentArgs>()

    private val component by lazy {
        (requireActivity().application as App).appComponent
    }
    private var _binding: DetailsFilmItemBinding? = null
    private val binding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DetailsFilmViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = DetailsFilmItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      binding.refreshButton.setOnClickListener {
          viewModel.refreshDetailsFilm()
      }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loadDetailsFilm(args.id)
                    .collect { stateDetailsFilm ->
                    when (stateDetailsFilm) {
                        is DetailsFilmState.Loading, DetailsFilmState.Initial ->
                            binding.progressBar.visibility = VISIBLE

                        is DetailsFilmState.Films -> {
                            binding.progressBar.visibility = GONE
                            val detailsFilm = stateDetailsFilm.detailsFilm

                            with(binding) {
                                emptyView.visibility = INVISIBLE
                                detailsFilmView.visibility = VISIBLE
                                filmName.text = detailsFilm.nameRu
                                filmDescription.text = detailsFilm.description
                                filmGenres.text =
                                    "Жанры: ${detailsFilm.genres.map { it.genre }.joinToString()}"
                                filmCountries.text = "Страны: ${
                                    detailsFilm.countries.map { it.country }.joinToString()
                                }"
                                Glide.with(requireContext())
                                    .load(detailsFilm.posterUrl)
                                    .centerCrop()
                                    .into(filmPoster)
                            }
                        }

                        is DetailsFilmState.Error -> {
                            with(binding) {
                                progressBar.visibility = GONE
                                emptyView.visibility = VISIBLE
                                detailsFilmView.visibility = GONE
                            }

                        }
                    }


                }
            }
        }
    }

}