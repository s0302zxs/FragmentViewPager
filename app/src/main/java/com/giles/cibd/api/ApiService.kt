/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.giles.cibd.api

import android.arch.lifecycle.LiveData
import com.giles.cibd.data.model.LoginResponse
import io.reactivex.Observable
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * REST API access points
 */
interface ApiService {
    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<RepoSearchResponse>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int, @Query("per_page") perPage: Int): Observable<Response<RepoSearchResponse>>

    @POST("api/login")
    fun login(@Query("username") username: String, @Query("password") password: String): Observable<Response<LoginResponse>>

    @POST("api/logout")
    fun logout(@Header("Cookie") cookie : String): Observable<Response<LoginResponse>>



}