package com.giles.cibd.dataSource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.giles.cibd.api.ApiService
import com.giles.cibd.data.model.NetworkState
import com.giles.cibd.data.model.Repo
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InformationDataSource : PageKeyedDataSource<Int, Repo> {

    private val compositeDisposable: CompositeDisposable
    private val apiService: ApiService

    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    var mQuery: String? = null
    var retryCompletable: Completable? = null

//    val retryCompletable = MutableLiveData<Completable>()

    @Inject
    constructor(apiService: ApiService, compositeDisposable: CompositeDisposable){
        Timber.d("InformationDataSource constructor")
        this.apiService = apiService
        this.compositeDisposable = compositeDisposable

    }

    fun retry(){
        Timber.d("retry retryCompletable = $retryCompletable")

    }

    fun setQueryString(queryString: String?) {
        mQuery = queryString
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
        Timber.d("loadInitial ")
        networkState.postValue(NetworkState.LOADING)

        setRetry(Action { loadInitial(params, callback) })


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        Timber.d("loadAfter")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
        Timber.d("loadBefore")
    }


    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }


}