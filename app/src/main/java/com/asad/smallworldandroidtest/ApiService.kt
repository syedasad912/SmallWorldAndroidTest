package com.asad.smallworldandroidtest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlODllZWFjZjUzYjg2ZmU2ZDViODI3NGY1YjM4Nzc1NyIsInN1YiI6IjVmMWMwODU0MGU1OTdiMDAzMzM1ZmQxYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8f3duHj-RCfxO9CKWtG3OjQUKuCwxoAcTxQW4cq5xj0" // Replace with your actual API key
    private const val BEARER_TOKEN = "Bearer $API_KEY" // Replace with your actual bearer token

    // Create an OkHttpClient with an Interceptor for adding headers
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", BEARER_TOKEN)
                    .method(original.method, original.body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }.addInterceptor(HttpLoggingInterceptor().apply {
                level=HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    // Lazy initialization of Retrofit with the OkHttpClient
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    // Define your service interface
    val apiService: ApiServiceInterface by lazy {
        retrofit.create(ApiServiceInterface::class.java)
    }



}
