package com.moises.resume.ui.skillset.di

import com.moises.data.skillset.datasource.SkillsetEndPoint
import com.moises.data.skillset.datasource.SkillsetRemoteDataSourceImpl
import com.moises.data.skillset.mapper.CourseItemToCourse
import com.moises.data.skillset.mapper.SkillItemToSkill
import com.moises.data.skillset.mapper.SkillsetResponseToSkillset
import com.moises.data.skillset.repository.SkillsetRepositoryImpl
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.skillset.datasource.SkillsetLocalDataSource
import com.moises.domain.skillset.datasource.SkillsetRemoteDataSource
import com.moises.domain.skillset.repository.SkillsetRepository
import com.moises.domain.skillset.usecase.SkillsetUseCase
import com.moises.presentation.skillset.SkillsetPresenter
import com.moises.presentation.skillset.SkillsetPresenterImpl
import com.moises.presentation.skillset.SkillsetView
import com.moises.resume.framework.datasource.skillset.CourseEntityToCourse
import com.moises.resume.framework.datasource.skillset.SkillsetLocalDataSourceImpl
import com.moises.resume.framework.db.DatabaseConfig
import com.moises.resume.framework.db.daos.CoursesDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SkillsetModule(private val skillsetView: SkillsetView) {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): SkillsetEndPoint =
        retrofit.create(SkillsetEndPoint::class.java)


    @Provides
    @Singleton
    fun provideCourseItemToCourseMapper(): CourseItemToCourse = CourseItemToCourse()


    @Provides
    @Singleton
    fun provideSkillItemToSkill(): SkillItemToSkill = SkillItemToSkill()


    @Provides
    @Singleton
    fun provideSkillsetResponseToSkillsetMapper(
        courseItemToCourse: CourseItemToCourse,
        skillItemToSkill: SkillItemToSkill
    ): SkillsetResponseToSkillset =
        SkillsetResponseToSkillset(courseItemToCourse, skillItemToSkill)

    @Provides
    @Singleton
    fun provideCourseEntityToCourse(): CourseEntityToCourse = CourseEntityToCourse()

    @Provides
    @Singleton
    fun provideCoursesDao(databaseConfig: DatabaseConfig): CoursesDao = databaseConfig.coursesDao()

    @Provides
    @Singleton
    fun provideSkillsetLocalDataSource(
        coursesDao: CoursesDao,
        courseEntityToCourse: CourseEntityToCourse
    ): SkillsetLocalDataSource = SkillsetLocalDataSourceImpl(coursesDao, courseEntityToCourse)

    @Provides
    @Singleton
    fun provideSkillsetDataSource(
        skillsetEndPoint: SkillsetEndPoint,
        skillsetResponseToSkillset: SkillsetResponseToSkillset
    ): SkillsetRemoteDataSource =
        SkillsetRemoteDataSourceImpl(skillsetEndPoint, skillsetResponseToSkillset)

    @Provides
    @Singleton
    fun provideSkillsetRepository(
        skillsetRemoteDataSource: SkillsetRemoteDataSource,
        skillsetLocalDataSource: SkillsetLocalDataSource
    ): SkillsetRepository =
        SkillsetRepositoryImpl(skillsetRemoteDataSource, skillsetLocalDataSource)

    @Provides
    @Singleton
    fun provideSkillsetUseCase(
        skillsetRepository: SkillsetRepository,
        jobScheduler: JobScheduler,
        uiScheduler: UIScheduler
    ): SkillsetUseCase = SkillsetUseCase(skillsetRepository, jobScheduler, uiScheduler)

    @Provides
    @Singleton
    fun provideSkillsetView(): SkillsetView = this.skillsetView

    @Provides
    @Singleton
    fun provideSkillsetPresenter(
        skillsetUseCase: SkillsetUseCase,
        skillsetView: SkillsetView
    ): SkillsetPresenter = SkillsetPresenterImpl(skillsetUseCase, skillsetView)
}