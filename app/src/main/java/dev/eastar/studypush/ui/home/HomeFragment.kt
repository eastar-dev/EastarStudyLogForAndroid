package dev.eastar.studypush.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import dev.eastar.studypush.databinding.FragmentHomeBinding
import smart.base.BFragment

@AndroidEntryPoint
class HomeFragment : BFragment() {
    private lateinit var bb: FragmentHomeBinding
    private val vm: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeBinding.inflate(inflater).also { bb = it }.root

    override fun onLoadOnce() {
        super.onLoadOnce()
        vm.text.observe(viewLifecycleOwner) {
            bb.textHome.text = it
        }
    }
}