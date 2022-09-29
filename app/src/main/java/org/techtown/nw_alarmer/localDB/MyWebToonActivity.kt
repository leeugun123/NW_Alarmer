package org.techtown.nw_alarmer.localDB

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import org.techtown.nw_alarmer.databinding.ActivityMyWebToonBinding

class MyWebToonActivity : AppCompatActivity() , OnItemClick {

    private lateinit var mBinding  : ActivityMyWebToonBinding

    private lateinit var adapter : MyWtListRecycler
    private lateinit var model : WTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMyWebToonBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        adapter = MyWtListRecycler(this)

        mBinding?.mylistView?.adapter = adapter

        initRecyclerView()

        model = ViewModelProvider(this).get(WTViewModel::class.java)
        //model 초기화 부분에 의해 초기화 되지 않았음

        with(model) {
            getAll().observe(this@MyWebToonActivity) { lists ->

                adapter.setList(lists)
                adapter.notifyDataSetChanged()

                Log.e("TAG","실행은 되는데...??ㄹㄷㄷㄷㄷㄻㅈㄷㄹㅈㅁㄹㄷㅈㅁㄻㅈㄻㅈㄹㅈㅁㄹㅈㅁㄷㄹㅈㄷㅁㄹ")

            }
        }



    }

    private fun initRecyclerView(){

        adapter = MyWtListRecycler(this)

        mBinding?.mylistView?.adapter = adapter

    }

    override fun deleteList(list: MyWtList) {
        TODO("Not yet implemented")
    }

}