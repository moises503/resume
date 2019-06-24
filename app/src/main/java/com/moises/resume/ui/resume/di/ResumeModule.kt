package com.moises.resume.ui.resume.di

import com.moises.data.model.mapper.ResumeResponseToProfile
import com.moises.data.provider.ResumeProvider
import com.moises.data.provider.ResumeProviderImpl
import com.moises.data.source.ResumeDataSource
import com.moises.data.source.ResumeDataSourceImpl
import com.moises.data.source.ResumeService
import com.moises.domain.executor.JobScheduler
import com.moises.domain.executor.UIScheduler
import com.moises.data.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.presentation.resume.ResumeView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ResumeModule(val resumeView: ResumeView) {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) : ResumeService{
        return retrofit.create(ResumeService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataSource(resumeService: ResumeService) : ResumeDataSource {
        return ResumeDataSourceImpl(resumeService)
    }

    @Provides
    @Singleton
    fun provideMapper() : ResumeResponseToProfile {
        return ResumeResponseToProfile()
    }

    @Provides
    @Singleton
    fun provideProvider(resumeDataSource: ResumeDataSource, resumeResponseToProfile: ResumeResponseToProfile)
    : ResumeProvider {
        return ResumeProviderImpl(resumeDataSource, resumeResponseToProfile)
    }

    @Provides
    @Singleton
    fun provideUseCase(resumeProvider: ResumeProvider, uiScheduler: UIScheduler, jobScheduler: JobScheduler)
            : ResumeUseCase {
        return ResumeUseCase(resumeProvider, uiScheduler, jobScheduler)
    }

    @Provides
    @Singleton
    fun provideView() : ResumeView {
        return resumeView
    }

    @Provides
    @Singleton
    fun providePresenter(resumeUseCase: ResumeUseCase, resumeView: ResumeView) : ResumePresenter {
        return ResumePresenterImpl(resumeUseCase, resumeView)
    }
}