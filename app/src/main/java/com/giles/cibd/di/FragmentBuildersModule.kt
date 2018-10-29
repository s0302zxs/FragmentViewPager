package com.giles.cibd.di

import com.giles.cibd.ui.RepoFragment
import com.giles.cibd.ui.information.InformationFragment
import com.giles.cibd.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoFragment

    @ContributesAndroidInjector
    abstract fun contributeInformationFragment(): InformationFragment
}