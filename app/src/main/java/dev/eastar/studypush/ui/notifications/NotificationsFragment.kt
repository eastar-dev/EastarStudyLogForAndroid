package dev.eastar.studypush.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.eastar.studypush.R
import dev.eastar.studypush.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private val notificationsViewModel by viewModels<NotificationsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = FragmentNotificationsBinding.inflate(inflater).root
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}