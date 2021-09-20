package com.example.firstoriontask


import com.example.UserData
import com.google.gson.JsonObject
import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.POST

interface Api {

    //urls
    @GET("/posts")
    fun getUsersData(): Call<List<UserData>>?

    @POST("/posts")
    fun sendUsersData(@Body JsonObject: JsonObject): Call<JsonObject>

}