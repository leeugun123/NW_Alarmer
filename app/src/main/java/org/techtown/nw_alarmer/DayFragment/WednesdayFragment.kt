package org.techtown.nw_alarmer.DayFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.techtown.nw_alarmer.R
import org.techtown.nw_alarmer.databinding.FragmentMondayBinding
import org.techtown.nw_alarmer.databinding.FragmentWednesdayBinding


class WednesdayFragment : Fragment() {


    private var mBinding : FragmentWednesdayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = FragmentWednesdayBinding.inflate(layoutInflater)
        mBinding = binding

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_wednesday,container,false)

        return rootView
    }
}