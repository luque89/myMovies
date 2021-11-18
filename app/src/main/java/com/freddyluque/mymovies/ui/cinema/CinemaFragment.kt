package com.freddyluque.mymovies.ui.cinema

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
import androidx.navigation.fragment.findNavController
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.CinemaFragmentBinding
import com.freddyluque.mymovies.databinding.ProfileFragmentBinding
import com.freddyluque.mymovies.network.NETWORK_STATUS
import com.freddyluque.mymovies.setFragmentBars
import com.freddyluque.mymovies.toMovieParcelable
import com.freddyluque.mymovies.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CinemaFragment : Fragment() {
    private val viewModel: CinemaViewModel by viewModels()
    private lateinit var binding: CinemaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.cinema_fragment,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapterItem = AdapterItem(MovieListener {
            findNavController().navigate(CinemaFragmentDirections.actionCinemaToMovieDetailFragment(it.toMovieParcelable()))
        })

        binding.recyclerViewMovies.adapter = adapterItem

        binding.btnTry.setOnClickListener {
            viewModel.getData()
        }

        viewModel.modelUi.observe(viewLifecycleOwner,{ event ->
            event.getContentIfNotHandled()?.let { action ->
                when(action){
                    is CinemaViewModel.UiModel.Network -> {
                        when(action.networkStatus) {
                            NETWORK_STATUS.DONE -> {
                                binding.progressCircular.visibility = View.GONE
                                binding.containerTryAgain.visibility = View.GONE
                            }
                            NETWORK_STATUS.LOADING -> {
                                binding.progressCircular.visibility = View.VISIBLE
                                binding.containerTryAgain.visibility = View.GONE
                            }
                            NETWORK_STATUS.ERROR -> {
                                binding.containerTryAgain.visibility = View.VISIBLE
                                binding.progressCircular.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setFragmentBars(
            activity as AppCompatActivity,
            AppBarVisible = true,
            bottomBarVisible = true
        )
    }

}