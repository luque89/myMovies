package com.freddyluque.mymovies.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.freddyluque.mymovies.R
import com.freddyluque.mymovies.databinding.LoginFragmentBinding
import com.freddyluque.mymovies.databinding.ProfileFragmentBinding
import com.freddyluque.mymovies.network.NETWORK_STATUS
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

        viewModel.modelUi.observe(viewLifecycleOwner,{ event ->
            event.getContentIfNotHandled()?.let { action ->
                when(action){
                    is ProfileViewModel.UiModel.UserGet -> {
                        binding.textViewUser.text = "${action.user.firstName} ${action.user.lastName}"
                        binding.textViewEmail.text = action.user.email
                        binding.textViewCardNumber.text = action.user.cardNumber
                    }
                    is ProfileViewModel.UiModel.Network -> {
                        when(action.networkStatus){
                            NETWORK_STATUS.DONE -> {
                                binding.progressCircular.visibility = View.GONE
                            }
                            NETWORK_STATUS.LOADING -> {
                                binding.progressCircular.visibility = View.VISIBLE
                                binding.textViewInfoErrorApi.visibility = View.GONE
                            }
                            NETWORK_STATUS.ERROR -> {
                                binding.textViewInfoErrorApi.visibility = View.VISIBLE
                                binding.textViewInfoErrorApi.text = action.error
                                binding.progressCircular.visibility = View.GONE
                            }
                        }
                    }
                    ProfileViewModel.UiModel.ShowBalance -> {
                        binding.recyclerViewBalance.visibility = View.VISIBLE
                    }
                }
            }
        })

        val adapterBalance = AdapterBalance()
        binding.recyclerViewBalance.adapter = adapterBalance

        binding.btnSave.setOnClickListener {
            if(binding.textInputEditTextPin.text.toString().isBlank() || binding.textInputEditTextCardNumber.text.toString().isBlank()){
                Toast.makeText(requireContext(),R.string.put_cardNumer_pin,Toast.LENGTH_LONG).show()
            }else{
                viewModel.getTransaction(binding.textInputEditTextCardNumber.text.toString(),binding.textInputEditTextPin.text.toString())
            }
        }
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