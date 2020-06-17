package com.massky.chars_s.service

import retrofit2.Call
import retrofit2.http.*


interface GitHubService {
    @GET("group/{id}/users")
    fun groupList(@Path("id") groupId: Int, @Query("sort") sort: String?): Call<List<User?>?>?


    @POST("users/new")
    fun createUser(@Body user: User?): Call<User?>?
}