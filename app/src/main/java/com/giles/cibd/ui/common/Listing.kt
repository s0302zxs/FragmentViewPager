package com.giles.cibd.ui.common

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.giles.cibd.data.model.NetworkState
import io.reactivex.Completable

data class Listing<T>(
        // the LiveData of paged lists for the UI to observe
        val pagedList: LiveData<PagedList<T>>?,
        // represents the network request status to show to the user
        val networkState: LiveData<NetworkState>?,
        // retries any failed requests.
//        val retry: LiveData<Completable>?,
        val retry: Completable?,
        // represents the refresh status to show to the user. Separate from networkState, this
        // value is importantly only when refresh is requested.
        val refreshState: LiveData<NetworkState>?
//        // refreshes the whole data and fetches it from scratch.
//        val refresh: () -> Unit,


)