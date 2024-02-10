package ru.tinkoff.lab.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.tinkoff.lab.data.local.db.AppDatabase
import ru.tinkoff.lab.data.local.db.FilmDao
import ru.tinkoff.lab.data.network.api.ApiService
import javax.inject.Singleton


@Module
class DataModule {
    private val BASE_URL = "https://kinopoiskapiunofficial.tech"
    private var cacheSize = (10 * 1024 * 1024).toLong() // 10 MB

    @Singleton
    @Provides
    fun provideRetrofit(okhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okhttp)
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
        .cache(Cache(context.applicationContext.cacheDir, cacheSize))
        .addInterceptor { chain ->
            val builder = chain.request().newBuilder()
            if (!isInternetAvailable(context)) {
                builder.cacheControl(CacheControl.FORCE_CACHE);
            }
            chain.proceed(builder.build());
        }
        .build()

    @Singleton
    @Provides

    fun provideFavouriteDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: AppDatabase): FilmDao {
        return database.filmDao()
    }
}