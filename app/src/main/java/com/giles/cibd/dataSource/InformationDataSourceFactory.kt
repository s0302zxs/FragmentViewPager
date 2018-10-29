package com.giles.cibd.dataSource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.giles.cibd.api.ApiService
import com.giles.cibd.data.model.Repo
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InformationDataSourceFactory : DataSource.Factory<Int, Repo>{
    private val apiService: ApiService
    private val compositeDisposable: CompositeDisposable
    private val informationDataSource : InformationDataSource
    private var mQuery: String? = null

    private val informationDataSourceLiveData = MutableLiveData<InformationDataSource>()

    @Inject
    constructor(apiService: ApiService, compositeDisposable: CompositeDisposable, informationDataSource: InformationDataSource){
        Timber.d("InformationDataSourceFactory constructor")
        this.apiService = apiService
        this.compositeDisposable = compositeDisposable
        this.informationDataSource = informationDataSource
//        informationDataSourceLiveData.postValue(this.informationDataSource)

    }

//    @Inject
//    constructor(apiService: ApiService, compositeDisposable: CompositeDisposable){
//        Timber.d("InformationDataSourceFactory constructor")
//        this.apiService = apiService
//        this.compositeDisposable = compositeDisposable
//
//    }

    override fun create(): DataSource<Int, Repo> {
        Timber.d("create")
        val informationDataSource = InformationDataSource(apiService, compositeDisposable)
//        RepoDataSource repoDataSource = new RepoDataSource(apiService);
//        Timber.d("RepoDataSourceFactory create repoDataSource = $repoDataSource mQuery = $mQuery")
        informationDataSource.setQueryString(mQuery)
        informationDataSourceLiveData.postValue(informationDataSource)

        return informationDataSource
    }


    fun setQueryString(query : String){
        Timber.d("setQueryString query = $query")
        mQuery = query

    }

    fun getInformationDataSourceLiveData(): MutableLiveData<InformationDataSource> {
        return informationDataSourceLiveData
    }
}