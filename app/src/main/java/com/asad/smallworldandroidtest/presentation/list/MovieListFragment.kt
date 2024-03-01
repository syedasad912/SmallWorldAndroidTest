package com.asad.smallworldandroidtest.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.asad.smallworldandroidtest.R
import com.asad.smallworldandroidtest.data.model.Result
import com.asad.smallworldandroidtest.data.repository.MovieRepository
import com.asad.smallworldandroidtest.databinding.FragmentMovieListBinding
import com.asad.smallworldandroidtest.presentation.MoviesListAdapter
import com.asad.smallworldandroidtest.presentation.MoviesViewModel
import com.asad.smallworldandroidtest.utils.ApiService
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {
    private var binding: FragmentMovieListBinding? = null

    private val moviesViewModel = MoviesViewModel(MovieRepository(ApiService))
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        getMoviesList()
        binding?.searchView?.doAfterTextChanged {
            filterMovie(it.toString())
        }
        return binding?.root
    }
    private fun filterMovie(movieName:String){
        lifecycleScope.launch {
            moviesViewModel.filterMovie(movieName)
            setupFilteredMovie()
        }
    }
    private fun setupFilteredMovie(){
        moviesViewModel.filteredMove.observe(viewLifecycleOwner) {
            setupMoviesListAdapter(it.results)
        }
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