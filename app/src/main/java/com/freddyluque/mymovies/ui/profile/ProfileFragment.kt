package com.freddyluque.mymovies.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.LoginFragmentBinding
import com.freddyluque.mymovies.databinding.ProfileFragmentBinding
import com.freddyluque.mymovies.setFragmentBars
import com.freddyluque.mymovies.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_fragment,container,false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setFragmentBars(
            activity as AppCompatActivity,
            AppBarVisible = true,
            bottomBarVisible = true
        )

        viewModel.modelUi.observe(viewLifecycleOwner,{ event ->
            event.getContentIfNotHandled()?.let { action ->
                when(action){
                    is ProfileViewModel.UiModel.UserGet -> {
                        binding.textViewUser.text = "${action.user.firstName} ${action.user.lastName}"
                        binding.textViewEmail.text = action.user.email
                        binding.textViewCardNumber.text = action.user.cardNumber
                    }
                }
            }
        })
        return binding.root
    }

}