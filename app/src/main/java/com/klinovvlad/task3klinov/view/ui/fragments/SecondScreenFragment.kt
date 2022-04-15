package com.klinovvlad.task3klinov.view.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.klinovvlad.task3klinov.databinding.FragmentSecondScreenBinding
import com.klinovvlad.task3klinov.utils.BUNDLE_PUT_PRIMARY_KEY
import com.klinovvlad.task3klinov.viewmodel.SecondScreenViewModel
import com.klinovvlad.task3klinov.viewmodel.SecondScreenViewModelFactory

class SecondScreenFragment : Fragment() {
    private lateinit var secondScreenBinding: FragmentSecondScreenBinding
    private val viewModel: SecondScreenViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            SecondScreenViewModelFactory(requireArguments().getString(BUNDLE_PUT_PRIMARY_KEY)!!)
        ).get(SecondScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        secondScreenBinding = FragmentSecondScreenBinding.inflate(
            inflater,
            container,
            false
        )
        return secondScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.showItem(requireContext())
        viewModel.item.observe(requireActivity()) {
            Glide.with(requireActivity())
                .load(it.picture.large)
                .into(secondScreenBinding.profilePicSecondscreen)
            secondScreenBinding.fullnameSecondscreen.text =
                "${it.name.title} ${it.name.first} ${it.name.last}"
            secondScreenBinding.phoneNumberSecondscreen.text = it.phone
            secondScreenBinding.emailSecondscreen.text = it.email
            secondScreenBinding.uuidSecondscreen.text = it.login.uuid
            secondScreenBinding.usernameSecondscreen.text = it.login.username
            secondScreenBinding.passwordSecondscreen.text = it.login.password

        }
    }
}