package org.techtown.nw_alarmer.localDB

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.nw_alarmer.R
import java.lang.Object
import org.techtown.nw_alarmer.databinding.FragmentMyBinding


class MyFragment : Fragment(), OnItemClick {

    private var mBinding : FragmentMyBinding? = null

    private lateinit var adapter : MyWtListRecycler
    private lateinit var model : WTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentMyBinding.inflate(layoutInflater)
        mBinding = binding

        model = ViewModelProvider(this).get(WTViewModel::class.java)

        initRecyclerView()


        //등록은 되는데 fragment에서 전혀 인지를 하지 못하고 있음
        model.getAll().observe(this, Observer<List<MyWtList>> {

            adapter.setList(it)
            adapter.notifyDataSetChanged()

            Log.e("TAG","실행은 되는데...??ㄹㄷㄷㄷㄷㄻㅈㄷㄹㅈㅁㄹㄷㅈㅁㄻㅈㄻㅈㄹㅈㅁㄹㅈㅁㄷㄹㅈㄷㅁㄹ")

        })

        //Log.e("TAG",model.toString())

    }

    private fun initRecyclerView(){

        adapter = MyWtListRecycler(this)

        mBinding?.mylistView?.adapter = adapter

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       return mBinding?.root
    }


    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }


    override fun deleteList(list: MyWtList) {
        TODO("Not yet implemented")
    }

}




