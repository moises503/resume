package com.moises.resume.core

import android.app.Activity
import android.support.v4.app.Fragment
import com.moises.data.core.executor.JobThread
import com.moises.domain.executor.JobScheduler
import com.moises.domain.executor.UIScheduler
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DiCoreModule {
    private lateinit var activity: Activity
    private lateinit var fragment: Fragment

    constructor(activity: Activity){
        this.activity = activity
    }

    constructor(fragment: Fragment){
        this.fragment = fragment
    }

    @Provides
    @Singleton
    fun providesActivity() : Activity {
        return this.activity
    }

    @Provides
    @Singleton
    fun providesFragment() : Fragment {
        return this.fragment
    }

    @Provides
    @Singleton
    fun providesHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder().addInterceptor(interceptor).addInterceptor {
            var request = it.request()
            val builder = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type","application/json")
                .method(request.method(), request.body())
            it.proceed(builder.build())
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(httpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/moises503/16c5e1a4572791e1eaf44ea5d3af7d48/raw/" +
                    "e849d7b3c0974ca466192242e5985106b1e024f6/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideJobThread() : JobScheduler {
        return JobThread()
    }

    @Provides
    @Singleton
    fun provideUIThread() : UIScheduler {
        return UIThread()
    }
}