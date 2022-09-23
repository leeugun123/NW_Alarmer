package org.techtown.nw_alarmer.localDB

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.databinding.FragmentMyBinding


class MyFragment : Fragment() {

    private var mBinding : FragmentMyBinding? = null

    private val myWebToonlist = mutableListOf<MyWtList>()

    lateinit var recyclerView : RecyclerView

    var db : ListDatabase? = null//myList DB

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentMyBinding.inflate(layoutInflater)
        mBinding = binding

        db = context?.let { ListDatabase.getInstance(it) }

        //요일 프래그먼트에서 불러온 리스트 업데이트
        val savedList = db!!.wtListDao().getList()
        myWebToonlist.addAll(listOf(savedList))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_my, container, false)

        recyclerView = rootView.findViewById(R.id.lstUser) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = MyWtListRecycler(myWebToonlist,requireContext())

        return rootView
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}


