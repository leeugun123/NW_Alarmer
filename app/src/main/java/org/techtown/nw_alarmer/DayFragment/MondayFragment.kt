package org.techtown.nw_alarmer.DayFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_monday.*
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.RecyclerWtAdapter
import org.techtown.nw_alarmer.WTData
import org.techtown.nw_alarmer.databinding.FragmentMondayBinding

class MondayFragment : Fragment() {

    private var mBinding : FragmentMondayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentMondayBinding.inflate(layoutInflater)
        mBinding = binding


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_monday,container,false)

        val list = ArrayList<WTData>()
        list.add(WTData("박태준","외모지상주의"))

        val adapter = RecyclerWtAdapter(list)
        lstUser.adapter = adapter


        return rootView
    }


}