package com.moises.resume.skilset.integration

import com.moises.data.skillset.datasource.SkillsetEndPoint
import com.moises.data.skillset.datasource.SkillsetRemoteDataSourceImpl
import com.moises.data.skillset.mapper.CourseItemToCourse
import com.moises.data.skillset.mapper.SkillItemToSkill
import com.moises.data.skillset.mapper.SkillsetResponseToSkillset
import com.moises.data.skillset.model.SkillsetResponse
import com.moises.data.skillset.repository.SkillsetRepositoryImpl
import com.moises.domain.skillset.datasource.SkillsetLocalDataSource
import com.moises.domain.skillset.datasource.SkillsetRemoteDataSource
import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.repository.SkillsetRepository
import com.moises.domain.skillset.usecase.SkillsetUseCase
import com.moises.presentation.skillset.SkillsetPresenter
import com.moises.presentation.skillset.SkillsetPresenterImpl
import com.moises.presentation.skillset.SkillsetView
import com.moises.resume.RxImmediateSchedulerRule
import com.moises.resume.core.job.JobThread
import com.moises.resume.core.job.UIThread
import com.moises.resume.framework.datasource.skillset.CourseEntityToCourse
import com.moises.resume.framework.datasource.skillset.SkillsetLocalDataSourceImpl
import com.moises.resume.framework.db.DatabaseBuilder
import com.moises.resume.framework.db.daos.CoursesDao
import com.moises.resume.skilset.faker.SkillsetSeeder
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class SkillsetIntegrationTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var skillsetEndPoint: SkillsetEndPoint
    lateinit var courseItemToCourse: CourseItemToCourse
    lateinit var skillItemToSkill: SkillItemToSkill
    lateinit var skillsetResponseToSkillset: SkillsetResponseToSkillset
    lateinit var skillsetResponse: SkillsetResponse
    lateinit var skillsetRemoteDataSource: SkillsetRemoteDataSource
    lateinit var courseEntityToCourse: CourseEntityToCourse
    lateinit var coursesDao: CoursesDao
    lateinit var skillsetLocalDataSource: SkillsetLocalDataSource
    lateinit var skillsetRepository: SkillsetRepository
    lateinit var skillsetUseCase : SkillsetUseCase
    lateinit var skillsetPresenter: SkillsetPresenter
    lateinit var skillsetView: SkillsetView

    @Before
    fun setup() {
        skillsetEndPoint = mock()
        skillsetResponse = SkillsetSeeder.getFakeSkillsetResponse()
        courseItemToCourse = CourseItemToCourse()
        skillItemToSkill = SkillItemToSkill()
        skillsetResponseToSkillset = SkillsetResponseToSkillset(courseItemToCourse, skillItemToSkill)
        skillsetResponse = SkillsetSeeder.getFakeSkillsetResponse()
        skillsetRemoteDataSource = SkillsetRemoteDataSourceImpl(skillsetEndPoint, skillsetResponseToSkillset)
        courseEntityToCourse = CourseEntityToCourse()
        //coursesDao = DatabaseBuilder().buildDatabase(InstrumentationRegistry.getContext())
        skillsetLocalDataSource = SkillsetLocalDataSourceImpl(coursesDao, courseEntityToCourse)
        skillsetRepository = SkillsetRepositoryImpl(skillsetRemoteDataSource, skillsetLocalDataSource)
        skillsetUseCase = SkillsetUseCase(skillsetRepository, JobThread(), UIThread())
        skillsetView = mock()
        skillsetPresenter = SkillsetPresenterImpl(skillsetUseCase, skillsetView)
    }

    @Test
    fun whenApiReturnsASkillsetObjectTheViewShouldShowThatObject() {
        val skillset = skillsetResponseToSkillset.transform(skillsetResponse)
        whenever(skillsetEndPoint.attemptGetSkillset()).thenReturn(Single.just(skillsetResponse))
        skillsetPresenter.retrieveSkillset(true)
        val captor : KArgumentCaptor<Skillset> = argumentCaptor()
        verify(skillsetView, times(1)).setSkillset(captor.capture())
        Assert.assertEquals(captor.firstValue, skillset)
    }
}