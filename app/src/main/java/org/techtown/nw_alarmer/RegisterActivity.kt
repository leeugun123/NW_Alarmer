package org.techtown.nw_alarmer

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.techtown.nw_alarmer.databinding.ActivityRegisterBinding
import org.techtown.nw_alarmer.localDB.MyWtList
import org.techtown.nw_alarmer.localDB.MyWtListRecycler
import org.techtown.nw_alarmer.localDB.WTViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    private lateinit var model : WTViewModel

    private lateinit var adapter : MyWtListRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(WTViewModel::class.java)

        val title = intent.getStringExtra("title")
        val img = intent.getStringExtra("img")

        binding.webTitle.text = title

        Glide.with(this)
            .load(img)
            .into(binding.webImg)




        binding.registerButton.setOnClickListener {

            Toast.makeText(this,"등록되었습니다.",Toast.LENGTH_SHORT).show()


            lifecycleScope.launch(Dispatchers.IO){
                model.insert(MyWtList(title,img,"up"))
                Log.e("TAG","등록됨")
            }//비동기로 구현

            finish()
        }


    }
}