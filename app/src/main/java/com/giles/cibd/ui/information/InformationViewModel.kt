package com.giles.cibd.ui.information

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class InformationViewModel @Inject
constructor(private val mInformationRepository: InformationRepository) : ViewModel() {
    private val isLoading = ObservableBoolean(false)
    private val compositeDisposable = CompositeDisposable()
    private val query = MutableLiveData<String>()

//    val networkState : LiveData<NetworkState> = {mInformationRepository?.getNetworkState()}

    //    val repos: LiveData<Resource<PagedList<Repo>>>
//    val repos: LiveData<PagedList<Repo>>


    private val repoResult = Transformations.map(query, {
        Timber.d("query change ")
        mInformationRepository.search(it, 1,30)
    })

    val repos = Transformations.switchMap(repoResult, { it.pagedList })!!
    val networkState = Transformations.switchMap(repoResult, { it.networkState })!!
    val refreshState = Transformations.switchMap(repoResult, { it.refreshState })!!

//    init {
//        repos = Transformations.switchMap(query) { userInput ->
//            Timber.d("asa")
////            if (userInput == null || userInput.isEmpty()) {
////                AbsentLiveData.create()
////            } else {
////                repository.search(userInput)
////            }
//            mInformationRepository.search(userInput, 1, 10)
////            return@switchMap null
//        }
//    }

    fun searchRepo(userInput: String) {
        Timber.d("searchRepo userInput = $userInput")
        query.value = userInput
    }


    fun retry() {
//        val listing = repoResult?.value
//        listing?.retry?.value.invoke()

        mInformationRepository.retry()
    }

//    internal fun getRepos(): LiveData<PagedList<Repo>> {
//        return repos
//    }

//    fun getNetworkState(): LiveData<NetworkState> {
//        return mInformationRepository?.getNetworkState()
//    }

}