package com.giles.cibd

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.giles.cibd.databinding.MainActivityBinding
import com.giles.cibd.di.Injectable
import com.giles.cibd.ui.announce.AnnouncementFragment
import com.giles.cibd.ui.cellSite.CellSiteFragment
import com.giles.cibd.ui.information.InformationFragment
import com.giles.cibd.ui.setting.SettingFragment
import com.giles.cibd.ui.tracking.TrackingFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    private var mBinding: MainActivityBinding? = null
    private var prevMenuItem: MenuItem? = null

    companion object {

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mBinding?.fragmentViewPager?.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mBinding?.fragmentViewPager?.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mBinding?.fragmentViewPager?.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_cell_site -> {
                mBinding?.fragmentViewPager?.currentItem = 3
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_setting -> {
                mBinding?.fragmentViewPager?.currentItem = 4
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        var selectFragment : Fragment
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                selectFragment = AnnouncementFragment.newInstance()
//            }
//            R.id.navigation_dashboard -> {
//                selectFragment = InformationFragment.newInstance()
//            }
//            R.id.navigation_notifications -> {
//                selectFragment = TrackingFragment.newInstance()
//            }
//            R.id.navigation_cell_site -> {
//                selectFragment = CellSiteFragment.newInstance()
//            }
//            else ->{
//                selectFragment = CellSiteFragment.newInstance()
//            }
//
//        }
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.frame_layout, selectFragment)
//        transaction.commit()
//        true
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        //for bottom layout average
        mBinding?.bottomNavigation?.disableShiftMode()
        mBinding?.bottomNavigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        initViewPager()

//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener(this, OnSuccessListener<InstanceIdResult> { instanceIdResult ->
//            val newToken = instanceIdResult.token
//            Timber.d("newToken = $newToken")
//        })
    }


    private fun initViewPager(){
        mBinding?.fragmentViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                Timber.d("prevMenuItem = $prevMenuItem position = $position")
                if (prevMenuItem != null) {
                    prevMenuItem?.isChecked = false
                } else {
                    mBinding?.bottomNavigation?.menu?.getItem(0)?.isChecked = false
                }
                mBinding?.bottomNavigation?.menu?.getItem(position)?.isChecked = true
                prevMenuItem = mBinding?.bottomNavigation?.menu?.getItem(position)


            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        val adapter = FragmentViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AnnouncementFragment.newInstance())
        adapter.addFragment(InformationFragment.newInstance())
        adapter.addFragment(TrackingFragment.newInstance())
        adapter.addFragment(CellSiteFragment.newInstance())
        adapter.addFragment(SettingFragment.newInstance())
        mBinding?.fragmentViewPager?.adapter = adapter

    }

    @SuppressLint("RestrictedApi")
    private fun BottomNavigationView.disableShiftMode() {
        val menuView = this.getChildAt(0) as BottomNavigationMenuView

        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false

            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}