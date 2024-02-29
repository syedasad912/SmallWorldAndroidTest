package com.asad.smallworldandroidtest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.asad.smallworldandroidtest.databinding.ItemMoviesListBinding

class MoviesListAdapter(private val context: Context) :
    RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {

    var listOfMovies: List<Result> = emptyList()
    var itemClickListener:  (Result) -> Unit = {}

    class MoviesListViewHolder(private val binding: ItemMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Result, context: Context) {
            val baseUrl = ApiService.IMAGE_BASE_URL
            val size = "w500"
            val posterPath = movie.posterPath
            val imageUrl = "$baseUrl$size$posterPath"

            Glide.with(context).load(imageUrl).into(binding.icon)
            binding.title.text = movie.title
            binding.popularity.text = movie.popularity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val binding = ItemMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesListViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfMovies.size

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.bind(listOfMovies[position], context)
        holder.itemView.setOnClickListener {
            itemClickListener(listOfMovies[position])
        }
    }
}
