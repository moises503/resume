package com.moises.resume.ui.resume.di

import com.moises.data.resume.datasource.EndPoint
import com.moises.data.resume.datasource.ResumeDataSourceImpl
import com.moises.data.resume.mapper.ResumeResponseToProfile
import com.moises.data.resume.repository.ResumeRepositoryImpl
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.resume.datasource.ResumeDataSource
import com.moises.domain.resume.datasource.ResumeLocalDataSource
import com.moises.domain.resume.repository.ResumeRepository
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.presentation.resume.ResumeView
import com.moises.resume.framework.datasource.resume.ResumeLocalDataSourceImpl
import com.moises.resume.framework.db.DatabaseConfig
import com.moises.resume.framework.db.daos.ExperiencesDao
import com.moises.resume.framework.db.daos.ProfilesDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ResumeModule(private val resumeView: ResumeView) {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): EndPoint {
        return retrofit.create(EndPoint::class.java)
    }

    @Provides
    @Singleton
    fun provideMapper(): ResumeResponseToProfile {
        return ResumeResponseToProfile()
    }

    @Provides
    @Singleton
    fun provideDataSource(endPoint: EndPoint, mapper: ResumeResponseToProfile): ResumeDataSource {
        return ResumeDataSourceImpl(endPoint, mapper)
    }

    @Provides
    @Singleton
    fun provideProfilesDao(databaseConfig: DatabaseConfig): ProfilesDao =
        databaseConfig.profilesDao()

    @Provides
    @Singleton
    fun provideExperiencesDao(databaseConfig: DatabaseConfig): ExperiencesDao =
        databaseConfig.experiencesDao()

    @Provides
    @Singleton
    fun provideResumeLocalDataSource(
        experiencesDao: ExperiencesDao,
        profilesDao: ProfilesDao
    ): ResumeLocalDataSource = ResumeLocalDataSourceImpl(experiencesDao, profilesDao)

    @Provides
    @Singleton
    fun provideRepository(
        resumeDataSource: ResumeDataSource,
        resumeLocalDataSource: ResumeLocalDataSource
    ): ResumeRepository {
        return ResumeRepositoryImpl(resumeDataSource, resumeLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideUseCase(
        resumeRepository: ResumeRepository,
        jobScheduler: JobScheduler,
        uiScheduler: UIScheduler
    ): ResumeUseCase {
        return ResumeUseCase(resumeRepository, jobScheduler, uiScheduler)
    }

    @Provides
    @Singleton
    fun provideView(): ResumeView {
        return resumeView
    }

    @Provides
    @Singleton
    fun providePresenter(resumeUseCase: ResumeUseCase, resumeView: ResumeView): ResumePresenter {
        return ResumePresenterImpl(resumeUseCase, resumeView)
    }
}

