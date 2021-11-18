package com.freddyluque.mymovies.ui.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.MovieDetailFragmentBinding
import com.freddyluque.mymovies.setFragmentBars
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.movie_detail_fragment,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.movie.resourceVideo?.let { url ->
            binding.viewVideo.setVideoPath(url)
            binding.viewVideo.start()
        }?:run {
            Toast.makeText(requireContext(),"No es posible reproducir el video, comprueba tu conexión de internet",Toast.LENGTH_LONG).show()
        }

        setFragmentBars(
            activity as AppCompatActivity,
            AppBarVisible = true,
            bottomBarVisible = false
        )

        return binding.root
    }

}