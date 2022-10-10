package com.example.geizgames.di

import android.content.Context
import androidx.room.Room
import com.example.geizgames.data.local.FavoriteDatabase
import com.example.geizgames.data.remote.* // ktlint-disable no-wildcard-imports
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    @Named("GameApi")
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun gameApi(@Named("GameApi") retrofit: Retrofit): GameApiService = retrofit.create(GameApiService::class.java)

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor {
        val newRequest: Request = it.request().newBuilder()
            .addHeader("X-RapidAPI-Key", API_TOKEN_SHOP)
            .addHeader("X-RapidAPI-Host", "game-prices.p.rapidapi.com")
            .build()
        it.proceed(newRequest)
    }.build()

    @Provides
    @Singleton
    @Named("ShopApi")
    fun provideRetrofitShop(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL_SHOP)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun shopApi(@Named("ShopApi") retrofit: Retrofit): ShopApiService = retrofit.create(ShopApiService::class.java)

    @Singleton @Provides
    fun provideFavoriteDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteDatabase::class.java,
        "favorite_table"
    ).build() @Singleton
    @Provides
    fun provideFavoriteDao(db: FavoriteDatabase) = db.getDao()
}
