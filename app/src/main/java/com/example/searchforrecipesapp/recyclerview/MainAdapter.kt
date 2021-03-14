package com.example.searchforrecipesapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchforrecipesapp.R
import com.example.searchforrecipesapp.dataclasses.Recipes
import kotlinx.android.synthetic.main.item_recipe.view.*

class MainAdapter(private var items: MutableList<Recipes>, val callback: Callback) :
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Recipes) {
            itemView.rv_name.text = item.name
            if (item.description.length > 250) {
                val ooo = " ..."
                itemView.rv_descr.text = item.description.substring(0..250) + ooo
            } else {
                itemView.rv_descr.text = item.description
            }
            Glide.with(itemView).load(item.images[0]).into(itemView.rv_image)
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: Recipes)
    }
}