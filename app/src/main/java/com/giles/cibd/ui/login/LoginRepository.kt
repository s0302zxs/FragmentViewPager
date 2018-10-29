package com.giles.cibd.ui.login

import com.giles.cibd.api.ApiService
import com.giles.cibd.data.local.PreferencesHelper
import com.giles.cibd.data.model.LoginResponse
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class LoginRepository{
    private val apiService: ApiService
    private val mPreferencesHelper: PreferencesHelper

    @Inject
    constructor(apiService: ApiService, preferencesHelper : PreferencesHelper){
        this.apiService = apiService
        mPreferencesHelper = preferencesHelper
    }

    fun login(username : String, password : String) : Observable<LoginResponse>{
        return loginApi(username, password)
    }

    fun logout() : Observable<LoginResponse>{
        return logoutApi(mPreferencesHelper.getCookie())
    }

    private fun loginApi(username : String, password : String) : Observable<LoginResponse> {
        return apiService.login(username, password)
                .subscribeOn(Schedulers.io())
                .map(object : Function<Response<LoginResponse>, LoginResponse> {
                    @Throws(Exception::class)
                    override fun apply(userDetailResponse: Response<LoginResponse>): LoginResponse? {
                        if(userDetailResponse.headers().get("Set-Cookie") != null){
                            mPreferencesHelper.setCookie(userDetailResponse.headers().get("Set-Cookie")!!)
                        }

                        return userDetailResponse.body()
                    }
                })
    }

    private fun logoutApi(cookie : String) : Observable<LoginResponse>{
        return apiService.logout(cookie)
                .subscribeOn(Schedulers.io())
                .map(object : Function<Response<LoginResponse>, LoginResponse> {
                    @Throws(Exception::class)
                    override fun apply(userDetailResponse: Response<LoginResponse>): LoginResponse? {
                        return userDetailResponse.body()
                    }
                })
    }

}