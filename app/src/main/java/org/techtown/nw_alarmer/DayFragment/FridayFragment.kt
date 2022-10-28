package org.techtown.nw_alarmer.DayFragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.techtown.nw_alarmer.*
import org.techtown.nw_alarmer.databinding.FragmentFridayBinding
import org.techtown.nw_alarmer.parsingClass.Parsing
import kotlin.random.Random


class FridayFragment : Fragment() {

    private var mBinding : FragmentFridayBinding? = null

    private var webToonlist : ArrayList<WTData> = ArrayList()
    lateinit var recyclerView : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentFridayBinding.inflate(layoutInflater)
        mBinding = binding

        webToonlist = Parsing().doTask(4,webToonlist)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_friday,container,false)

        recyclerView = rootView.findViewById(R.id.lstUser) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerWtAdapter(webToonlist,requireContext())

        return rootView
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


}