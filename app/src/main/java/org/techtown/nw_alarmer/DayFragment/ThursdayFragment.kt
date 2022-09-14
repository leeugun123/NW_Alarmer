package org.techtown.nw_alarmer.DayFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.databinding.FragmentMondayBinding
import org.techtown.nw_alarmer.databinding.FragmentThursdayBinding
import org.techtown.nw_alarmer.databinding.FragmentWednesdayBinding


class ThursdayFragment : Fragment() {

    private var mBinding : FragmentThursdayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentThursdayBinding.inflate(layoutInflater)
        mBinding = binding

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_thursday,container,false)


        return rootView
    }

}