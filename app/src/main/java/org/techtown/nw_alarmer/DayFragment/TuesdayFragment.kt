package org.techtown.nw_alarmer.DayFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.techtown.nw_alarmer.JsoupCrawlerExample
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.RecyclerWtAdapter
import org.techtown.nw_alarmer.WTData
import org.techtown.nw_alarmer.databinding.FragmentTuesdayBinding
import org.techtown.nw_alarmer.parsingClass.Parsing


class TuesdayFragment : Fragment() {

    private var mBinding : FragmentTuesdayBinding? = null

    private var webToonlist : ArrayList<WTData> = ArrayList()
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentTuesdayBinding.inflate(layoutInflater)
        mBinding = binding

        webToonlist = Parsing().doTask(1,webToonlist)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_tuesday, container, false)

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