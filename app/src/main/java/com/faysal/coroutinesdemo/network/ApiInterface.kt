package com.faysal.coroutinesdemo.network

import com.faysal.coroutinesdemo.models.Comments
import com.faysal.coroutinesdemo.models.Photo
import com.faysal.coroutinesdemo.models.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    suspend fun getPosts() : Response<Post>


    @GET("comments")
    suspend fun getComments() : Response<Comments>

    @GET("photos")
    suspend fun getPhotos() : Response<Photo>

}