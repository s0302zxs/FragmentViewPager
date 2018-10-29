package com.giles.cibd.ui.tracking

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giles.cibd.databinding.TrackingFragmentBinding

class TrackingFragment : Fragment(){
    private var mBinding: TrackingFragmentBinding? = null

    companion object {
        val TAG = "TrackingFragment"

        fun newInstance() = TrackingFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = TrackingFragmentBinding.inflate(inflater, container, false)

        return mBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




    }
}