package com.asad.smallworldandroidtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.asad.smallworldandroidtest.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {
    private var binding: FragmentMovieListBinding? = null

    private val moviesViewModel = MoviesViewModel(MovieRepository(ApiService))
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        getMoviesList()
        return binding?.root
    }

    private fun setupMoviesListAdapter(moviesList: ArrayList<Result>) {
        binding?.rvMoviesList?.adapter = MoviesListAdapter(requireContext()).apply {
            listOfMovies = moviesList
            itemClickListener = {
                findNavController().navigate(
                    R.id.action_listFragment_to_detailsFragment,
                    Bundle().apply { putLong("movieId", it.id.toLong()) })
            }
        }
    }

    private fun getMoviesList() {
        moviesViewModel.movie.observe(requireActivity()) { movie ->
            setupMoviesListAdapter(movie.results)
        }
    }
}