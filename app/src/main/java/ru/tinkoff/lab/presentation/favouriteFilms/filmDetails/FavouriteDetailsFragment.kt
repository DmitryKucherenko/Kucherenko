package ru.tinkoff.lab.presentation.favouriteFilms.filmDetails

import android.graphics.drawable.Drawable
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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.launch
import ru.tinkoff.lab.App
import ru.tinkoff.lab.databinding.FavouriteDetailsItemBinding
import ru.tinkoff.lab.domain.state.FilmDetailsState
import ru.tinkoff.lab.presentation.ViewModelFactory
import javax.inject.Inject

class FavouriteDetailsFragment : Fragment() {

    private val args by navArgs<FavouriteDetailsFragmentArgs>()

    private val component by lazy {
        (requireActivity().application as App).appComponent
    }
    private var _binding: FavouriteDetailsItemBinding? = null
    private val binding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = FavouriteDetailsItemBinding.inflate(inflater, container, false)
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
                            is FilmDetailsState.Loading ->
                                binding.progressBar.visibility = VISIBLE

                            is FilmDetailsState.Success -> {
                                val detailsFilm = stateDetailsFilm.filmDetails

                                with(binding) {
                                    emptyView.visibility = INVISIBLE
                                    detailsFilmView.visibility = VISIBLE
                                    filmName.text = detailsFilm.nameRu
                                    filmDescription.text = detailsFilm.description
                                    filmGenres.text =
                                        "Жанры: ${
                                            detailsFilm.genres.joinToString()
                                        }"
                                    filmCountries.text = "Страны: ${
                                        detailsFilm.countries.joinToString()
                                    }"
                                    Glide.with(requireContext())
                                        .load(detailsFilm.posterUrl)
                                        .listener(object : RequestListener<Drawable> {
                                            override fun onLoadFailed(
                                                e: GlideException?,
                                                model: Any?,
                                                target: Target<Drawable>?,
                                                isFirstResource: Boolean
                                            ): Boolean {
                                                binding.progressBar.visibility = GONE
                                                return false
                                            }
                                            override fun onResourceReady(
                                                resource: Drawable?,
                                                model: Any?,
                                                target: Target<Drawable>?,
                                                dataSource: DataSource?,
                                                isFirstResource: Boolean
                                            ): Boolean {
                                                binding.progressBar.visibility = GONE
                                                return false
                                            }
                                        })
                                        .centerCrop()
                                        .into(filmPoster)
                                }
                            }

                            is FilmDetailsState.Error -> {
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