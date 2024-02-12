package ru.tinkoff.lab.presentation.favouriteFilms.filmList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.tinkoff.lab.databinding.FavouriteItemBinding
import ru.tinkoff.lab.domain.model.Film


class FavouriteFilmAdapter(
    private val itemClickListener: (Film) -> Unit,
    private val itemLongClickListener: (Film) -> Unit
) :
    ListAdapter<Film, FavouriteHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavouriteItemBinding.inflate(layoutInflater, parent, false)
        return FavouriteHolder(itemClickListener, itemLongClickListener, binding)
    }

    override fun onBindViewHolder(holder: FavouriteHolder, position: Int) {
        val film = currentList[position]
        holder.bind(film)
    }
}