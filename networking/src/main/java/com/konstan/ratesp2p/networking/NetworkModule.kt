package com.konstan.ratesp2p.networking

import com.konstan.ratesp2p.networking.repository.p2p.P2PRepository
import com.konstan.ratesp2p.networking.repository.p2p.P2PRepositoryImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://p2p.binance.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(mOkHttpClient)
            .build()
    }

    @Provides
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    fun provideP2PRepository(service: NetworkService): P2PRepository {
        return P2PRepositoryImpl(service)
    }
}