package com.klinovvlad.task3klinov.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.klinovvlad.task3klinov.R
import com.klinovvlad.task3klinov.databinding.FragmentSecondScreenBinding
import com.klinovvlad.task3klinov.utils.BUNDLE_USER_UUID
import com.klinovvlad.task3klinov.viewmodel.SecondScreenViewModel
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.scope.emptyState
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class SecondScreenFragment : Fragment() {
    private lateinit var secondScreenBinding: FragmentSecondScreenBinding
    private val viewModel: SecondScreenViewModel by viewModel {
        parametersOf(
            requireArguments().getString(
                BUNDLE_USER_UUID
            ) ?: ""
        )
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
        viewModel.getUser()
        viewModel.user.observe(viewLifecycleOwner) {
            with(secondScreenBinding) {
                Glide.with(view)
                    .load(it.large)
                    .into(profilePicSecondscreen)
                fullnameSecondscreen.text = getString(
                    R.string.full_name,
                    it.title,
                    it.first,
                    it.last
                )
                phoneNumberSecondscreen.text = it.phone
                emailSecondscreen.text = it.email
                uuidSecondscreen.text = it.uuid
                usernameSecondscreen.text = it.username
                passwordSecondscreen.text = it.password
            }
        }
    }
}