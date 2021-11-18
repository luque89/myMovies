package com.freddyluque.mymovies.ui.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.MovieDetailFragmentBinding
import com.freddyluque.mymovies.setFragmentBars
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.view.*


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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shared_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share ->{
                val message = "Quieres ir al cine a ver: ${viewModel.movie.name}?"
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, message)

                startActivity(Intent.createChooser(share, "Title of the dialog the system will open"))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        setFragmentBars(
            activity as AppCompatActivity,
            AppBarVisible = true,
            bottomBarVisible = false
        )
        viewModel.movie.resourceVideo?.let { url ->
            binding.viewVideo.setVideoPath(url)
            binding.viewVideo.start()
        }
    }

}