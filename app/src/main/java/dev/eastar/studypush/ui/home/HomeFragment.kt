package dev.eastar.studypush.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import dev.eastar.studypush.databinding.FragmentHomeBinding
import javax.inject.Inject

class HomeFragment : Fragment() {

    //@Inject
    //lateinit var analytics: AnalyticsAdapter
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })

        lifecycle.addObserver(observer)

        lifecycleScope.launchWhenCreated {

        }

        return binding.root
    }

    private val observer: LifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun connectListener() {

        }
    }
}