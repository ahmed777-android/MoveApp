package com.example.moveapp.di.module

import com.example.moveapp.Api_Key
import com.example.moveapp.BASE_URL
import com.example.moveapp.networking.MovieApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkingModule {

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newUrl = chain.request()
                .url.newBuilder()
                .addQueryParameter("api_key", Api_Key)
                .build()
            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }
    }


    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
    @Singleton
    @Provides
    fun provideMoshi():Moshi{
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }


        @Singleton
        @Provides
        fun getRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(okHttpClient )
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl(BASE_URL)
                .build()

        }

        @Provides
        @Singleton
        fun getApiCallInterface(retrofit: Retrofit): MovieApi =
            retrofit.create(MovieApi::class.java)

    }