package org.techtown.nw_alarmer.localDB

import android.media.CamcorderProfile.getAll
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.nw_alarmer.databinding.ActivityMyWebToonBinding

class MyWebToonActivity : AppCompatActivity() , OnItemClick {

    private lateinit var mBinding  : ActivityMyWebToonBinding

    private lateinit var adapter : MyWtListRecycler
    private lateinit var model : WTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMyWebToonBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initRecyclerView()

        model = ViewModelProvider(this).get(WTViewModel::class.java)
        //model 초기화 부분에 의해 초기화 되지 않았음

        with(model) {

            getAll().observe(this@MyWebToonActivity) { lists ->

                adapter.setList(lists)
                adapter.notifyDataSetChanged()

                Log.e("TAG","데이터 감지")

            }

        }




    }

    private fun initRecyclerView(){

        adapter = MyWtListRecycler(this)

        mBinding?.mylistView?.adapter = adapter

    }

    override fun deleteList(list: MyWtList) {

            lifecycleScope.launch(Dispatchers.IO){
                model.delete(list)
                Log.e("TAG","액티비티에서 삭제")
            }

    }

    override fun updateList(list: MyWtList) {

        lifecycleScope.launch(Dispatchers.IO) {
            model.update(list)
            Log.e("TAG","액티비티에서 update")
        }

    }


}