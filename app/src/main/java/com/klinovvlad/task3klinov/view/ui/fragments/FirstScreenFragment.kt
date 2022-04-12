package com.klinovvlad.task3klinov.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.klinovvlad.task3klinov.databinding.FragmentFirstScreenBinding
import com.klinovvlad.task3klinov.network.api.MainApi
import com.klinovvlad.task3klinov.network.instances.MainInstance
import com.klinovvlad.task3klinov.view.adapters.MainAdapter
import com.klinovvlad.task3klinov.viewmodel.FirstScreenViewModel
import com.klinovvlad.task3klinov.viewmodel.FirstScreenViewModelFactory
import kotlinx.coroutines.awaitAll

class FirstScreenFragment : Fragment() {
    private lateinit var firstScreenBinding: FragmentFirstScreenBinding
    private val viewModel: FirstScreenViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            FirstScreenViewModelFactory(MainInstance)
        ).get(FirstScreenViewModel::class.java)
    }
    private val mainAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firstScreenBinding = FragmentFirstScreenBinding.inflate(
            inflater,
            container,
            false
        )
        return firstScreenBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData()
        firstScreenBinding.recyclerviewMain.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
        viewModel.dataList.observe(requireActivity()) {
            mainAdapter.submitList(it)
        }
    }

}