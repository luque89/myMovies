package com.freddyluque.mymovies.ui.cinema

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freddyluque.domain.Movie
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.ElementMovieBinding

class AdapterItem(private val clickListener: MovieListener): ListAdapter<Movie, AdapterItem.CartHolder>(CartDiffCallback()) {
    class CartHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ElementMovieBinding.bind(view)

        fun render(movie: Movie,clickListener: MovieListener){
            binding.movie = movie.copy()
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CartHolder(layoutInflater.inflate(R.layout.element_movie, parent, false))
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val item = getItem(position)
        holder.render(item,clickListener)
    }
}

class CartDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}

class MovieListener(val clickListener: (item: Movie) -> Unit) {
    fun onClick(item: Movie) = clickListener(item)
}