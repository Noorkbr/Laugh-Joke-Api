package com.example.laughjoke.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.laughjoke.databinding.ItemLayoutBinding
import com.example.laughjoke.db.JokesData


class JokesAdapter(
    private val jokeList: List<JokesData>,
    private var handleUserClick: HandleUserClick
) : RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    interface HandleUserClick {
        fun onDeleteClick(jokesData: JokesData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        jokeList[position].let { itemData ->
            holder.binding.apply {
                if (itemData.joke == null) {
                    jokeSetup.text = itemData.setup
                    delivery.text = itemData.delivery
                    type.text = "Category: ${itemData.type}"
                } else {
                    delivery.text = itemData.joke
                    type.text = "Category: ${itemData.type}"
                }
                btnFavorite.setOnClickListener {
                    handleUserClick.onDeleteClick(itemData)
                }
            }
        }
    }

    inner class JokesViewHolder(var binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}