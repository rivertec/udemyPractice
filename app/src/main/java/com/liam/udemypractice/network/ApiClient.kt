package com.liam.udemypractice.network

import com.liam.udemypractice.model.Category
import com.liam.udemypractice.model.CategoryDetail
import com.liam.udemypractice.model.Product
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("categories.json")
    suspend fun getCategories(): List<Category>

    @GET("{categoryId}.json")
    suspend fun getCategoryDetail(@Path("categoryId") categoryId: String?): CategoryDetail
//    @GET("fashion_female.json")
//    suspend fun getCategoryDetail(): CategoryDetail

    @GET("products/{productId}.json")
    suspend fun getProductsDetail(@Path("productId") productId: String): Product
//    @GET("products/desk-1.json")
//    suspend fun getProductsDetail(): Product

    companion object {

        private const val baseUrl = "https://udymyproject-default-rtdb.firebaseio.com/"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}