package com.giles.cibd.ui.announce

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giles.cibd.databinding.AnnouncementFragmentBinding

class AnnouncementFragment : Fragment(){
    private var mBinding: AnnouncementFragmentBinding? = null

    companion object {
        val TAG = "AnnouncementFragment"

        fun newInstance() = AnnouncementFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = AnnouncementFragmentBinding.inflate(inflater, container, false)

        return mBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




    }
}