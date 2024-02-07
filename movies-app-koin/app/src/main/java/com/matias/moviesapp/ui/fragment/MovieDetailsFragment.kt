package com.matias.moviesapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import coil.load
import com.matias.domain.entity.MovieDetails
import com.matias.domain.entity.error.NoNetworkException
import com.matias.domain.entity.error.NotFoundException
import com.matias.moviesapp.R
import com.matias.moviesapp.databinding.FragmentMovieDetailsBinding
import com.matias.moviesapp.ui.entity.AlertData
import com.matias.moviesapp.util.DialogUtil
import com.matias.moviesapp.viewmodel.MovieDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MovieDetailsFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModel()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup legacy action bar
        NavigationUI.setupActionBarWithNavController(requireActivity() as AppCompatActivity, findNavController())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovie(args.movieId)
        viewModel.movieData.observe(viewLifecycleOwner, ::updateUI)
    }

    private fun updateUI(movieData: MovieDetailsViewModel.MovieData) {
        when (movieData.state) {
            MovieDetailsViewModel.MovieData.State.SUCCESS -> movieData.data?.let { showDetails(it) }
            MovieDetailsViewModel.MovieData.State.FAILURE -> showError(movieData.error)
            MovieDetailsViewModel.MovieData.State.LOADING -> showLoading()
        }
    }

    private fun showDetails(movie: MovieDetails) {
        binding.apply {
            progressLoader.root.visibility = View.GONE
            movieDetailsLayout.visibility = View.VISIBLE
            movieTitle.text = movie.title
            movieBackdrop.load(movie.backdropUrl) {
                crossfade(true)
            }
            moviePoster.load(movie.posterUrl) {
                crossfade(true)
            }
            movieVotes.text = String.format("%.2f", movie.voteAverage)
            movieReleaseDate.text = movie.releaseDate
            moviePlaytime.text = getString(R.string.movie_playtime, movie.runtime ?: 0)
        }
    }

    private fun showLoading() {
        binding.progressLoader.root.visibility = View.VISIBLE
        binding.movieDetailsLayout.visibility = View.GONE
    }

    private fun showError(throwable: Throwable?) {
        throwable?.let { handleError(it) }
        // TODO: design an error layout
    }

    private fun handleError(error: Throwable) {
        val message: String = when (error) {
            is NoNetworkException -> getString(R.string.msg_error_network)
            is NotFoundException -> getString(R.string.msg_error_not_found)
            else -> error.message ?: getString(R.string.msg_error_not_found)
        }

        val alert = AlertData(
            title = getString(R.string.title_error_default),
            message = message
        )
        context?.let { DialogUtil.showDialog(it, alert) }
    }
}
