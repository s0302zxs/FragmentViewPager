package com.giles.cibd.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.giles.cibd.CIBDApp
import com.giles.cibd.api.ApiService
import com.giles.cibd.data.db.GithubDb
import com.giles.cibd.data.db.GithubDb.Companion.MIGRATION_1_2
import com.giles.cibd.data.db.RepoDao
import com.giles.cibd.data.local.AppPreferencesHelper
import com.giles.cibd.data.local.PreferencesHelper
import com.giles.cibd.util.AppConstants
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule {
    private val application: Application

    constructor(application: Application){
        this.application = application

    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
                .baseUrl("http://192.168.10.204:8081/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }


    @Provides
    @Singleton
    fun provideDb(app: CIBDApp): GithubDb {
        return Room.databaseBuilder(app, GithubDb::class.java, "github.db")
                .addMigrations(MIGRATION_1_2)
                .build()
    }

    @Provides
    @Singleton
    fun provideRepoDao(db: GithubDb): RepoDao {
        return db.repoDao()
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return this.application
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

}