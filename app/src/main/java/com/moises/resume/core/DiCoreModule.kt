package com.moises.resume.core

import android.app.Activity
import androidx.fragment.app.Fragment
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.resume.core.job.JobThread
import com.moises.resume.core.job.UIThread
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                .method(request.method, request.body)
            it.proceed(builder.build())
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(httpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/moises503/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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