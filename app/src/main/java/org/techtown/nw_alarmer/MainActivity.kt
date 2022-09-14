package org.techtown.nw_alarmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.nw_alarmer.DayFragment.*
import org.techtown.nw_alarmer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PagerAdapter(supportFragmentManager)

        adapter.addFragment(MondayFragment(), "월")
        adapter.addFragment(TuesdayFragment(), "화")
        adapter.addFragment(WednesdayFragment(), "수")
        adapter.addFragment(ThursdayFragment(), "목")
        adapter.addFragment(FridayFragment(), "금")
        adapter.addFragment(SaturdayFragment(), "토")
        adapter.addFragment(SundayFragment(), "일")

        after_login_viewpager.adapter = adapter
        after_login_tablayout.setupWithViewPager(after_login_viewpager)



    }
}