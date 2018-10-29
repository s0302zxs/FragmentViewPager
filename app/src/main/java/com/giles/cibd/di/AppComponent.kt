package com.giles.cibd.di

import android.app.Application
import com.giles.cibd.CIBDApp
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class))
interface AppComponent {

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: CIBDApp): Builder
//
//        fun build(): AppComponent
//    }

    fun application(): Application
    fun inject(app: CIBDApp)
}