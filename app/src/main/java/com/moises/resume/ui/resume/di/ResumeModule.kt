package com.moises.resume.ui.resume.di
import com.moises.data.resume.datasource.EndPoint
import com.moises.data.resume.datasource.ResumeRemoteDataSource
import com.moises.data.resume.datasource.ResumeRemoteDataSourceImpl
import com.moises.data.resume.repository.ResumeRepository
import com.moises.data.resume.repository.ResumeRepositoryImpl
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.resume.mapper.ResumeResponseToProfile
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.presentation.resume.ResumeView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ResumeModule (val resumeView: ResumeView) {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) : EndPoint{
        return retrofit.create(EndPoint::class.java)
    }

    @Provides
    @Singleton
    fun provideDataSource(endPoint: EndPoint) : ResumeRemoteDataSource {
        return ResumeRemoteDataSourceImpl(endPoint)
    }

    @Provides
    @Singleton
    fun provideMapper() : ResumeResponseToProfile {
        return ResumeResponseToProfile()
    }

    @Provides
    @Singleton
    fun provideRepository(resumeRemoteDataSource: ResumeRemoteDataSource)
            : ResumeRepository {
        return ResumeRepositoryImpl(resumeRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideUseCase(resumeRepository: ResumeRepository, jobScheduler: JobScheduler, uiScheduler: UIScheduler,
                       mapper : ResumeResponseToProfile)
            : ResumeUseCase {
        return ResumeUseCase(resumeRepository, jobScheduler, uiScheduler, mapper)
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
