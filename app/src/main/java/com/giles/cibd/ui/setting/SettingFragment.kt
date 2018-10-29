package com.giles.cibd.ui.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giles.cibd.databinding.SettingFragmentBinding

class SettingFragment : Fragment(){
    private var mBinding: SettingFragmentBinding? = null

    companion object {
        val TAG = "SettingFragment"

        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = SettingFragmentBinding.inflate(inflater, container, false)

        return mBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




    }
}