package org.techtown.nw_alarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.nw_alarmer.DayFragment.*
import org.techtown.nw_alarmer.databinding.ActivityMainBinding
import org.techtown.nw_alarmer.localDB.MyWebToonActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val adapter = PagerAdapter(supportFragmentManager)

        adapter.addFragment(MondayFragment(), "월")
        adapter.addFragment(TuesdayFragment(), "화")
        adapter.addFragment(WednesdayFragment(), "수")
        adapter.addFragment(ThursdayFragment(), "목")
        adapter.addFragment(FridayFragment(), "금")
        adapter.addFragment(SaturdayFragment(), "토")
        adapter.addFragment(SundayFragment(), "일")
        //adapter.addFragment(MyFragment(),"MY")

        after_login_viewpager.adapter = adapter
        after_login_tablayout.setupWithViewPager(after_login_viewpager)

        mBinding .myWebButton.setOnClickListener {

            val intent = Intent(this, MyWebToonActivity::class.java)
            startActivity(intent)

        }


    }
}