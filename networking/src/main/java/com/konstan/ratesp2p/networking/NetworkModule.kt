package com.konstan.ratesp2p.networking

import XmlOrJsonConverterFactory
import com.konstan.ratesp2p.networking.repository.p2p.P2PRepository
import com.konstan.ratesp2p.networking.repository.p2p.P2PRepositoryImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.konstan.ratesp2p.networking.repository.currency.CurrencyRepository
import com.konstan.ratesp2p.networking.repository.currency.CurrencyRepositoryImpl
import com.konstan.ratesp2p.networking.repository.currency.CurrencyService
import com.konstan.ratesp2p.networking.repository.p2p.P2PService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class XmlResponse

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class JsonResponse

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
            .baseUrl("http://localhost/")
            .addConverterFactory(
                MultipleConverterFactory.Builder()
                    .setXmlConverterFactory(SimpleXmlConverterFactory.create())
                    .setJsonConverterFactory(GsonConverterFactory.create())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(mOkHttpClient)
            .build()
    }

    @Provides
    fun provideNetworkService(retrofit: Retrofit): P2PService {
        return retrofit.create(P2PService::class.java)
    }

    @Provides
    fun provideP2PRepository(service: P2PService): P2PRepository {
        return P2PRepositoryImpl(service)
    }

    @Provides
    fun provideCurrencyNetworkService(retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }

    @Provides
    fun provideCurrencyRepository(service: CurrencyService): CurrencyRepository {
        return CurrencyRepositoryImpl(service)
    }
}