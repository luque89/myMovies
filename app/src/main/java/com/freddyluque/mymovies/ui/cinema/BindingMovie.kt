package com.freddyluque.mymovies.ui.cinema

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freddyluque.domain.Movie
import com.freddyluque.mymovies.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


@BindingAdapter("tileItem")
fun bindTextTitle(textView: TextView, title: String) {
    textView.text = title
}

@BindingAdapter("imageItem")
fun bindImage(imgView: ImageView, imageUrl: String?) {
    imageUrl?.let { image ->
        Picasso.get()
            .load(image)
            .into(imgView)
    }?: run {
        imgView.setImageDrawable(imgView.context.getDrawable(R.drawable.ic_action_not_image))
    }
}

@BindingAdapter("movieListData")
fun bindRecyclerViewProducts(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as AdapterItem
    adapter.submitList(data)
}