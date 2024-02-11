package ru.tinkoff.lab.presentation.filmList.previewFilmList.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.tinkoff.lab.domain.model.PreviewFilm

object DiffCallBack : DiffUtil.ItemCallback<PreviewFilm>() {
    override fun areItemsTheSame(oldItem: PreviewFilm, newItem: PreviewFilm): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: PreviewFilm, newItem: PreviewFilm): Boolean {
        return oldItem == newItem
    }
}