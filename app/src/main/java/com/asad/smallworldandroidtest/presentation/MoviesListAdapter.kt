package com.asad.smallworldandroidtest.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asad.smallworldandroidtest.R
import com.asad.smallworldandroidtest.data.model.Result
import com.asad.smallworldandroidtest.databinding.ItemMoviesListBinding
import com.asad.smallworldandroidtest.utils.ApiService
import com.bumptech.glide.Glide

class MoviesListAdapter(private val context: Context) :
    RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder>() {
    var posterSize: String = "w500"
    var listOfMovies: List<Result> = emptyList()
    var itemClickListener:  (Result) -> Unit = {}

    class MoviesListViewHolder(private val binding: ItemMoviesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Result, posterSize: String, context: Context) {
            val baseUrl = ApiService.IMAGE_BASE_URL
            val posterPath = movie.posterPath
            val imageUrl = "$baseUrl$posterSize$posterPath"

            Glide.with(context).load(imageUrl).into(binding.icon)
            binding.title.text = context.getString(R.string.title).plus(movie.title)
            binding.popularity.text =
                context.getString(R.string.popularity).plus(movie.popularity.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        val binding = ItemMoviesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesListViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfMovies.size

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        holder.bind(listOfMovies[position], posterSize, context)
        holder.itemView.setOnClickListener {
            itemClickListener(listOfMovies[position])
        }
    }
}
