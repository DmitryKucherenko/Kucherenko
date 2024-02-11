package ru.tinkoff.lab.presentation.filmList.previewFilmList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.tinkoff.lab.databinding.PreviewFilmItemBinding
import ru.tinkoff.lab.domain.model.PreviewFilm

class PreviewFilmAdapter(
    private val itemClickListener: (PreviewFilm) -> Unit,
    private val itemLongClickListener: (PreviewFilm) -> Unit
) :
    ListAdapter<PreviewFilm, PFilmHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PFilmHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PreviewFilmItemBinding.inflate(layoutInflater, parent, false)
        return PFilmHolder(itemClickListener, itemLongClickListener, binding)
    }

    override fun onBindViewHolder(holder: PFilmHolder, position: Int) {
        val previewFilm = currentList[position]
        holder.bind(previewFilm)
    }
}