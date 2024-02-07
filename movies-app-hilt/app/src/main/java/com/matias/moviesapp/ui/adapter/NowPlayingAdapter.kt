package com.matias.moviesapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.matias.domain.entity.Movie
import com.matias.moviesapp.databinding.ListItemNowPlayingBinding

class NowPlayingAdapter(
    private val items: List<Movie>
) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    private var onMovieClicked: (Int) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNowPlayingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setOnMovieClickedListener(listener: (Int) -> Unit) {
        onMovieClicked = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemNowPlayingBinding.bind(itemView)

        fun bind(item: Movie) {
            binding.apply {
                cvNowPlayingTitle.text = item.title

                cvNowPlayingImage.scaleType = ImageView.ScaleType.CENTER_CROP
                cvNowPlayingImage.load(item.posterUrl) {
                    crossfade(true)
                }
                root.setOnClickListener { onMovieClicked(item.id) }
            }
        }
    }
}
