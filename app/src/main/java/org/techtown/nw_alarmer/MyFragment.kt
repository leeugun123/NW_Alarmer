package org.techtown.nw_alarmer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.nw_alarmer.databinding.FragmentMyBinding
import org.techtown.nw_alarmer.localDB.MyWtList
import org.techtown.nw_alarmer.localDB.MyWtListRecycler


class MyFragment : Fragment() {

    private var mBinding : FragmentMyBinding? = null

    private val myWebToonlist : ArrayList<MyWtList> = ArrayList()
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentMyBinding.inflate(layoutInflater)
        mBinding = binding

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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