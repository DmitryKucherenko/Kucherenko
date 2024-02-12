package ru.tinkoff.lab.presentation.films.filmList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.tinkoff.lab.data.local.db.FilmDao
import ru.tinkoff.lab.databinding.FilmItemBinding
import ru.tinkoff.lab.domain.model.Film

class PreviewFilmAdapter(
    private val isFavourite: (Film) -> Boolean,
    private val itemClickListener: (Film) -> Unit,
    private val itemLongClickListener: (Film) -> Unit
) :
    ListAdapter<Film, PFilmHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PFilmHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(layoutInflater, parent, false)
        return PFilmHolder(itemClickListener, itemLongClickListener, binding)
    }

    override fun onBindViewHolder(holder: PFilmHolder, position: Int) {
        val previewFilm = currentList[position]
        val isFavourite = isFavourite(previewFilm)
        holder.bind(previewFilm, isFavourite)
    }
}