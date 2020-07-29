package dev.eastar.studypush.ui.home

import android.icu.text.SimpleDateFormat
import android.log.Log
import android.os.Build
import android.os.Bundle
import android.recycler.BindingDataArrayAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.eastar.studypush.BR
import dev.eastar.studypush.R
import dev.eastar.studypush.data.model.StudyItemList
import dev.eastar.studypush.databinding.FragmentHomeBinding
import smart.base.BFragment
import java.util.*

@AndroidEntryPoint
class HomeFragment : BFragment() {
    private lateinit var bb: FragmentHomeBinding
    private val vm: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bb = FragmentHomeBinding.inflate(inflater)
        bb.lifecycleOwner = this
        bb.vm = vm
        return bb.root
    }
}

@Suppress("unused")
@BindingAdapter("data")
fun setData(view: RecyclerView, menu: StudyItemList?) {
    Log.e(view, menu)
    if (view.adapter !is BindingDataArrayAdapter)
        view.adapter = BindingDataArrayAdapter(R.layout.fragment_home_item, BR.studyitem)
    (view.adapter as BindingDataArrayAdapter).set(menu)
}


@Suppress("unused")
@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("dateformat", "date")
fun setDate(view: AppCompatTextView, format: String, date: Long) {
    Log.w(view, format, date)
    view.text = SimpleDateFormat(format, Locale.getDefault()).format(Date().apply { time = date })
}