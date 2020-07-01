package dev.eastar.studypush.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import dagger.hilt.android.AndroidEntryPoint
import dev.eastar.studypush.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var bb: FragmentHomeBinding
    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bb = FragmentHomeBinding.inflate(inflater)
        vm.text.observe(viewLifecycleOwner, Observer {
            bb.textHome.text = it
        })

        lifecycle.addObserver(observer)

        lifecycleScope.launchWhenCreated {

        }

        return bb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.onLoad()
    }

    private val observer: LifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun connectListener() {

        }
    }
}