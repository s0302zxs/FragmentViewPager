package com.giles.cibd

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.giles.cibd.di.Injectable
import com.giles.cibd.ui.login.LoginFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class LandingActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_activity)
        val tag = LoginFragment.Companion.TAG
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment = LoginFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commitAllowingStateLoss()
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector



}