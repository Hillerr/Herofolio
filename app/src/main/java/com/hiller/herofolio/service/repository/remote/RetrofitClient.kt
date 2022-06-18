package com.hiller.herofolio.service.repository.remote

import com.hiller.herofolio.R
import com.hiller.herofolio.service.constants.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

class RetrofitClient private constructor() {
    companion object {
        private lateinit var retrofit: Retrofit
        private val base_url = "https://gateway.marvel.com/v1/public/"
        private val authInterceptor = {chain : Interceptor.Chain ->
            val ts = System.currentTimeMillis()
            val hash = "$ts${AppConstants.KEY.privateKey}${AppConstants.KEY.apikey}".md5()
            val request = chain.request()
            val url = request.url()
                .newBuilder()
                .addQueryParameter("ts",ts.toString())
                .addQueryParameter("apikey", AppConstants.KEY.apikey)
                .addQueryParameter("hash", hash)
                .build()
            val updated = request.newBuilder().url(url).build()
            chain.proceed(updated)
        }

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder().addInterceptor(authInterceptor)

            if(!Companion::retrofit.isInitialized){
                retrofit = Retrofit.Builder()
                    .baseUrl(base_url)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S{
            return getRetrofitInstance().create(serviceClass)
        }

        private fun String.md5(): String =
            BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
                .toString(16).padStart(32, '0')
    }
}