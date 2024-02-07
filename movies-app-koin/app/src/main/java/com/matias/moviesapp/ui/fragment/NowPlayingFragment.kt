package com.matias.moviesapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.matias.domain.entity.Movie
import com.matias.domain.entity.error.NoNetworkException
import com.matias.domain.entity.error.NotFoundException
import com.matias.moviesapp.R
import com.matias.moviesapp.databinding.FragmentNowPlayingBinding
import com.matias.moviesapp.ui.adapter.NowPlayingAdapter
import com.matias.moviesapp.ui.entity.AlertData
import com.matias.moviesapp.util.DialogUtil
import com.matias.moviesapp.viewmodel.NowPlayingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class NowPlayingFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = NowPlayingFragment()
        private const val GRID_COLUMNS = 3
    }

    private lateinit var binding: FragmentNowPlayingBinding
    private val viewModel: NowPlayingViewModel by viewModel()

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
        binding = FragmentNowPlayingBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNowPlayingMovies()
        viewModel.moviesData.observe(viewLifecycleOwner, ::updateUI)

        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigation)
    }

    private fun handleNavigation(navigation: NowPlayingViewModel.Navigation) {
        when (navigation) {
            is NowPlayingViewModel.Navigation.ToMovieDetails -> {
                val action = NowPlayingFragmentDirections.actionNowPlayingFragmentToMovieDetailsFragment(navigation.movieId)
                findNavController().navigate(action)
            }
        }
    }

    private fun updateUI(movieData: NowPlayingViewModel.MoviesData) {
        when (movieData.state) {
            NowPlayingViewModel.MoviesData.State.SUCCESS -> showMovies(movieData.data)
            NowPlayingViewModel.MoviesData.State.EMPTY -> showEmpty()
            NowPlayingViewModel.MoviesData.State.FAILURE -> showError(movieData.error)
            NowPlayingViewModel.MoviesData.State.LOADING -> showLoading()
        }
    }

    private fun showMovies(movieList: List<Movie>) {
        val adapter = NowPlayingAdapter(movieList)
        adapter.setOnMovieClickedListener(viewModel::onMoviePressed)

        binding.apply {
            progressLoader.root.visibility = View.GONE
            emptyView.root.visibility = View.GONE
            nowPlayingRecyclerView.layoutManager = GridLayoutManager(context, GRID_COLUMNS)
            nowPlayingRecyclerView.adapter = adapter
        }
    }

    private fun showLoading() {
        binding.progressLoader.root.visibility = View.VISIBLE
    }

    private fun showEmpty() {
        binding.progressLoader.root.visibility = View.GONE
        binding.emptyView.root.visibility = View.VISIBLE
    }

    private fun showError(throwable: Throwable?) {
        throwable?.let { handleError(it) }
        showEmpty()
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
