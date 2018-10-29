package com.giles.cibd.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giles.cibd.MainActivity
import com.giles.cibd.databinding.LoginFragmentBinding
import com.giles.cibd.di.Injectable
import com.giles.cibd.viewmodel.CIBDViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() , Injectable {

    private var mBinding: LoginFragmentBinding? = null
    @Inject
    lateinit var factory: CIBDViewModelFactory

//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    companion object {
        val TAG = "LoginFragment"

        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = LoginFragmentBinding.inflate(inflater, container, false)

        mBinding?.btnLogin?.setOnClickListener {view ->
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()

//            loginViewModel.login("test", "test")
        }

//        mBinding?.btnLogout?.setOnClickListener {view ->
//            loginViewModel.logout()
//        }


        return mBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel = ViewModelProviders.of(this, factory)
                .get(LoginViewModel::class.java)

        loginViewModel.loginResponse.observe(this, Observer { it ->
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        })


    }


}