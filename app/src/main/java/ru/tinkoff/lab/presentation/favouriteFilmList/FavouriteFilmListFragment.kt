package ru.tinkoff.lab.presentation.favouriteFilmList

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkclientnews.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import ru.tinkoff.lab.App
import ru.tinkoff.lab.databinding.PreviewListFilmsBinding
import ru.tinkoff.lab.presentation.filmList.previewFilmList.adapter.PreviewFilmAdapter
import javax.inject.Inject

class FavouriteFilmListFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).appComponent
    }
    private var _binding: PreviewListFilmsBinding? = null
    private val binding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[FavouriteFilmListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        component.inject(this)
        _binding = PreviewListFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmsRecyclerView = binding.previewFilmRecyclerView
        filmsRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter =
            PreviewFilmAdapter(
                { previewFilm -> Log.d("Film", previewFilm.toString()) },
                { previewFilm -> Log.d("Film", previewFilm.toString()) })

        filmsRecyclerView.adapter = adapter
        binding.refreshButton.setOnClickListener {
            viewModel.refreshFilmList()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loadPreviewFilmsList
                    .collect { filmListState ->
                        when (filmListState) {
                            is FilmListState.Loading -> {
                                binding.emptyView.visibility = GONE
                                binding.progressBarLoading.visibility = VISIBLE
                            }

                            is FilmListState.Films -> {
                                binding.emptyView.visibility = GONE
                                binding.progressBarLoading.visibility = GONE
                                adapter.submitList(filmListState.filmsList)
                            }

                            is FilmListState.Initial -> {
                                binding.emptyView.visibility = GONE
                                binding.progressBarLoading.visibility = VISIBLE
                            }

                            is FilmListState.Error -> {
                                binding.progressBarLoading.visibility = GONE
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