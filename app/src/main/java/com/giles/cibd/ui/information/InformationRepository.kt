package com.giles.cibd.ui.information

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.giles.cibd.api.ApiService
import com.giles.cibd.data.model.NetworkState
import com.giles.cibd.data.model.Repo
import com.giles.cibd.dataSource.InformationDataSource
import com.giles.cibd.dataSource.InformationDataSourceFactory
import com.giles.cibd.ui.common.Listing
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InformationRepository{

    private val apiService: ApiService
    private val mInformationDataSourceFactory : InformationDataSourceFactory
//    private val networkState = MutableLiveData<NetworkState>()
//    private val compositeDisposable: CompositeDisposable

    @Inject
    constructor(apiService: ApiService, informationDataSourceFactory: InformationDataSourceFactory){
        Timber.d("constructor mInformationDataSourceFactory")
        this.apiService = apiService
//        this.compositeDisposable = compositeDisposable
        mInformationDataSourceFactory = informationDataSourceFactory
    }


    fun search(query : String, page : Int, perPage : Int) : Listing<Repo> {
        return getReposFromApiPaging(query, page, perPage)
    }

    fun retry(){
        Timber.d("retry")
        mInformationDataSourceFactory?.getInformationDataSourceLiveData()?.value?.retry()
    }


    private fun getReposFromApiPaging(query : String, page : Int, perPage : Int) : Listing<Repo> {

        val config = PagedList.Config.Builder()
                .setPageSize(perPage)
                .setInitialLoadSizeHint(perPage)
                .setEnablePlaceholders(false)
                .build()

        Timber.d("getReposFromApiPaging query = $query")
        Timber.d("getReposFromApiPaging mInformationDataSourceFactory = $mInformationDataSourceFactory")
//        mInformationDataSourceFactory.setQueryString(query)
//        mInformationDataSourceFactory.create()

        val builder = LivePagedListBuilder(mInformationDataSourceFactory, config)

        return Listing(
                pagedList = builder.build(),

                networkState = Transformations.switchMap<InformationDataSource, NetworkState>(mInformationDataSourceFactory?.getInformationDataSourceLiveData(),
                        {it.networkState}),
                retry = mInformationDataSourceFactory?.getInformationDataSourceLiveData()?.value?.retryCompletable,
//                retry = Transformations.switchMap<InformationDataSource, Completable>(mInformationDataSourceFactory?.getInformationDataSourceLiveData(),
//                        {it.retryCompletable}),
//                refreshState = mInformationDataSourceFactory?.getInformationDataSourceLiveData()?.value?.initialLoad
                refreshState = Transformations.switchMap<InformationDataSource, NetworkState>(mInformationDataSourceFactory?.getInformationDataSourceLiveData(),
                        {it.initialLoad})
        )



    }




}