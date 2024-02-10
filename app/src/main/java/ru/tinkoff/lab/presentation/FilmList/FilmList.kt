package ru.tinkoff.lab.presentation.FilmList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vkclientnews.presentation.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.tinkoff.lab.App
import ru.tinkoff.lab.databinding.PreviewListFilmsBinding
import javax.inject.Inject
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import ru.tinkoff.lab.domain.model.PreviewFilm
import ru.tinkoff.lab.presentation.FilmList.adapter.ItemClickListener
import ru.tinkoff.lab.presentation.FilmList.adapter.PreviewFilmAdapter

class FilmList : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).appComponent
    }
    private var _binding: PreviewListFilmsBinding? = null
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
        _binding = PreviewListFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var filmsRecyclerView  = binding.previewFilmRecyclerView
        filmsRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = PreviewFilmAdapter(object : ItemClickListener {
            override fun onItemClick(previewFilm: PreviewFilm?) {
                //TODO
            }
        })
        filmsRecyclerView.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getPreviewFilmsList().collect {listPreviewFilm->
                    Log.d("FilmList",listPreviewFilm.toString())
                    adapter.submitList(listPreviewFilm)
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}