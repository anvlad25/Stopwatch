package com.example.stopwatch.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.stopwatch.R
import com.example.stopwatch.databinding.MainFragmentBinding
import com.example.stopwatch.model.*
import com.example.stopwatch.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val binding: MainFragmentBinding by viewBinding()
    private val viewModel: MainViewModel by viewModel()
    private var stopwatchListOrchestrator: StopwatchListOrchestrator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData.observe(viewLifecycleOwner) {
            stopwatchListOrchestrator = it
        }

        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        ).launch {
            stopwatchListOrchestrator?.ticker?.collect {
                binding.textTime.text = it
            }
        }

        with(binding) {
            buttonStart.setOnClickListener {
                stopwatchListOrchestrator?.start()
            }
            buttonPause.setOnClickListener {
                stopwatchListOrchestrator?.pause()
            }
            buttonStop.setOnClickListener {
                stopwatchListOrchestrator?.stop()
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}