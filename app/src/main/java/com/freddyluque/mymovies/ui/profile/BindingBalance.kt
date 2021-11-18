package com.freddyluque.mymovies.ui.profile

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freddyluque.domain.Balance
import com.freddyluque.domain.Movie
import com.freddyluque.mymovies.ui.cinema.AdapterItem

@BindingAdapter("balance")
fun bindTextBalance(textView: TextView, balance: Double) {
    textView.text = balance.toString()
}

@BindingAdapter("key")
fun bindTextKey(textView: TextView, key: String) {
    textView.text = key
}

@BindingAdapter("nameBalance")
fun bindTextNameBalance(textView: TextView, name: String) {
    textView.text = name
}

@BindingAdapter("message")
fun bindTextMessage(textView: TextView, message: String) {
    textView.text = message
}

@BindingAdapter("balanceListData")
fun bindRecyclerViewProducts(recyclerView: RecyclerView, data: List<Balance>?) {
    val adapter = recyclerView.adapter as AdapterBalance
    adapter.submitList(data)
}