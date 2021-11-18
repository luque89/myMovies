package com.freddyluque.mymovies.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.LoginFragmentBinding
import com.freddyluque.mymovies.network.NETWORK_STATUS
import com.freddyluque.mymovies.setFragmentBars
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.login_fragment,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.modelUi.observe(viewLifecycleOwner,{ event ->
            event.getContentIfNotHandled()?.let { action ->
                when(action){
                    is LoginViewModel.UiModel.Network -> {
                        when(action.networkStatus) {
                            NETWORK_STATUS.DONE -> {
                                binding.progressCircular.visibility = View.GONE
                                binding.textViewInfoErrorApi.visibility = View.GONE
                            }
                            NETWORK_STATUS.LOADING -> {
                                binding.progressCircular.visibility = View.VISIBLE
                                binding.textViewInfoErrorApi.visibility = View.GONE
                            }
                            NETWORK_STATUS.ERROR -> {
                                binding.progressCircular.visibility = View.GONE
                                binding.textViewInfoErrorApi.visibility = View.VISIBLE
                                binding.textViewInfoErrorApi.text = action.error
                            }
                        }
                    }
                    is LoginViewModel.UiModel.NavigateToProfile -> {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProfileFragment())
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
            AppBarVisible = false,
            bottomBarVisible = false
        )
    }

}