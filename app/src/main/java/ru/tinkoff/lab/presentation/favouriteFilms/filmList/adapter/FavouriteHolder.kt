package ru.tinkoff.lab.presentation.favouriteFilms.filmList.adapter

import android.graphics.drawable.Drawable
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.tinkoff.lab.databinding.FavouriteItemBinding
import ru.tinkoff.lab.domain.model.Film

class FavouriteHolder(
    private val itemClickListener: (Film) -> Unit,
    private val itemLongClickListener: (Film) -> Unit,
    binding: FavouriteItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val filmName = binding.filmName
    private val filmGenre = binding.filmGanre
    private val previewIcon = binding.previewIcon
    private val favouriteIcon = binding.favouriteIcon
    private val previewFilmProgress = binding.previewProgressBar

    fun bind(film: Film) {
        filmName.text = film.nameRu
        val genre = film.genres.firstOrNull()?.replaceFirstChar { it.uppercaseChar() } ?: ""
            .replaceFirstChar { it.uppercaseChar() }
        val year = film.year
        filmGenre.text = "$genre($year)"
        Glide.with(itemView)
            .load(film.posterUrlPreview)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    previewFilmProgress.visibility = GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    previewFilmProgress.visibility = GONE
                    return false
                }
            })
            .centerCrop()
            .into(previewIcon)

        itemView.setOnClickListener {
            itemClickListener(film)
        }
        itemView.setOnLongClickListener {
            favouriteIcon.visibility = if (favouriteIcon.isVisible) GONE else VISIBLE
            itemLongClickListener(film)
            true
        }
    }


}