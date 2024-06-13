package org.d3if3165.assessment3aprilia.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3165.assessment3aprilia.model.Bunga
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "liavaprilia/bunga/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BungaApiService {
    @GET("static-api.json")
    suspend fun getBunga(): List<Bunga>
}

object BungaApi {
    val service: BungaApiService by lazy {
        retrofit.create(BungaApiService::class.java)
    }

    fun getBunga(imageId: String): String {
        return "$BASE_URL$imageId.jpeg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }