package com.giles.cibd.ui.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.giles.cibd.data.model.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel : ViewModel{
    private var mLoginRepository : LoginRepository? = null
    private var mCompositeDisposable : CompositeDisposable
    var loginResponse : MutableLiveData<LoginResponse> = MutableLiveData()

    @Inject
    constructor(loginRepository : LoginRepository, compositeDisposable : CompositeDisposable) {
        mLoginRepository = loginRepository
        mCompositeDisposable = compositeDisposable

    }

    fun login(username : String, password : String){
        Timber.d("username = $username , password = $password")

        val disposableObserver = object : DisposableObserver<LoginResponse>() {
            override fun onNext(value: LoginResponse) {
                Timber.d("onNext value = $value")
                Timber.d("onNext value.code = ${value.code}")
                Timber.d("onNext value.message = ${value.message}")
                //check isSuccessful here
//                setRetry(null)
//                userDetailLiveData.setValue(value)

                //check code
                loginResponse.value = value


            }

            override fun onError(e: Throwable) {
                Timber.d("onError e = $e")
//                setRetry { getUserDetail(username) }
//                userDetailLiveData.setValue(null)
//                initialLoad.setValue(NetworkState.Companion.error(e.message))
            }

            override fun onComplete() {
                Timber.d("onComplete")
//                initialLoad.setValue(NetworkState.Companion.getLOADED())
            }

        }

        mCompositeDisposable?.add(mLoginRepository!!.login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver))


//        mLoginRepository.login(username, password)
    }

    fun logout(){
        val disposableObserver = object : DisposableObserver<LoginResponse>() {
            override fun onNext(value: LoginResponse) {
                Timber.d("onNext value = $value")
                Timber.d("onNext value.code = ${value.code}")
                Timber.d("onNext value.message = ${value.message}")
                //check isSuccessful here
//                setRetry(null)
//                userDetailLiveData.setValue(value)

                //check code
            }

            override fun onError(e: Throwable) {
                Timber.d("onError e = $e")
                Timber.d("onError e.message = ${e.message}")

//                Timber.d("onError e.message = ${e.}")
//                setRetry { getUserDetail(username) }
//                userDetailLiveData.setValue(null)
//                initialLoad.setValue(NetworkState.Companion.error(e.message))
            }

            override fun onComplete() {
                Timber.d("onComplete")
//                initialLoad.setValue(NetworkState.Companion.getLOADED())
            }

        }

        mCompositeDisposable?.add(mLoginRepository!!.logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver))
    }


    fun test(){
        Timber.d("test mLoginRepository = $mLoginRepository")
    }
}