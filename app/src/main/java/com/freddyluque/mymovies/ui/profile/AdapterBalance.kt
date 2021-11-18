package com.freddyluque.mymovies.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freddyluque.domain.Balance
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.ElementBalanceBinding

class AdapterBalance(): ListAdapter<Balance, AdapterBalance.CartHolder>(CartDiffCallback()) {
    class CartHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ElementBalanceBinding.bind(view)

        fun render(movie: Balance){
            binding.balance = movie.copy()
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CartHolder(layoutInflater.inflate(R.layout.element_balance, parent, false))
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val item = getItem(position)
        holder.render(item)
    }
}

class CartDiffCallback: DiffUtil.ItemCallback<Balance>() {
    override fun areItemsTheSame(oldItem: Balance, newItem: Balance): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Balance, newItem: Balance): Boolean {
        return oldItem == newItem
    }

}