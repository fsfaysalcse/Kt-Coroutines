package com.faysal.coroutinesdemo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkBuilder {
    companion object{
        val BASE_URL : String = "https://jsonplaceholder.typicode.com/"

        fun getInstance() : Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}