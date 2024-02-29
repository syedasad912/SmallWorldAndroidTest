package com.asad.smallworldandroidtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.asad.smallworldandroidtest.databinding.FragmentMovieDetailBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {
    private var movieId: Long = 0
    private var binding: FragmentMovieDetailBinding? = null
    private val moviesViewModel = MoviesViewModel(MovieRepository(ApiService))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundle()
    }

    private fun getBundle() {
        movieId = arguments?.getLong("movieId", 0) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        getMovieDetail(movieId)
        return binding?.root
    }

    private fun setupMovieDetail() {
        val baseUrl = ApiService.IMAGE_BASE_URL
        val size = "original"
        moviesViewModel.movieDetail.observe(requireActivity()) {
            val posterPath = it.posterPath
            val imageUrl = "$baseUrl$size$posterPath"
            binding?.imagePoster?.let { it1 ->
                Glide.with(requireActivity()).load(imageUrl).into(
                    it1
                )
            }
            binding?.textTitle?.text = it.title
            binding?.textOverview?.text = it.overview
            binding?.textGenres?.text = getString(R.string.genre).plus(it.genres?.joinToString { it1 -> it1.name.toString() })
            binding?.textReleaseDate?.text = getString(R.string.release_date).plus(it.releaseDate)
            binding?.textProductionCountries?.text = getString(R.string.production_country).plus(it.productionCountries?.joinToString { it.name.toString() })
            binding?.textProductionCompanies?.text = getString(R.string.production_company).plus(it.productionCompanies?.joinToString { it.name.toString() })
            binding?.textSpokenLanguages?.text = getString(R.string.language).plus(it.spokenLanguages?.joinToString { it.englishName.toString() })
        }
    }

    private fun getMovieDetail(movieId: Long) {
        lifecycleScope.launch {
            moviesViewModel.fetchMovieDetail(movieId)
            setupMovieDetail()
        }
    }


}