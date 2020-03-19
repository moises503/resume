package com.moises.resume.resume.integration

import com.moises.data.resume.datasource.EndPoint
import com.moises.data.resume.datasource.ResumeDataSourceImpl
import com.moises.data.resume.mapper.ResumeResponseToProfile
import com.moises.data.resume.model.Resume
import com.moises.data.resume.repository.ResumeRepositoryImpl
import com.moises.domain.resume.datasource.ResumeDataSource
import com.moises.domain.resume.datasource.ResumeLocalDataSource
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.repository.ResumeRepository
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.presentation.resume.ResumeView
import com.moises.resume.RxImmediateSchedulerRule
import com.moises.resume.core.job.JobThread
import com.moises.resume.core.job.UIThread
import com.moises.resume.resume.faker.ResumeSeeder
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class ResumeIntegrationTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var presenter : ResumePresenter
    lateinit var repository : ResumeRepository
    lateinit var useCase : ResumeUseCase
    lateinit var dataSource : ResumeDataSource
    lateinit var localDataSource: ResumeLocalDataSource
    lateinit var mapper : ResumeResponseToProfile
    lateinit var resume : Resume

    lateinit var view : ResumeView
    lateinit var endPoint: EndPoint


    @Before
    fun setup() {
        view = mock()
        endPoint = mock()
        resume = ResumeSeeder.makeFakeData()
        mapper = ResumeResponseToProfile()
        dataSource = ResumeDataSourceImpl(endPoint, mapper)
        localDataSource = mock()
        repository = ResumeRepositoryImpl(dataSource, localDataSource)
        useCase =  ResumeUseCase(repository, JobThread(), UIThread())
        presenter = ResumePresenterImpl(useCase, view)
    }

    @Test
    fun whenApiReturnsResumeItIsPropagatedToTheViewAsIs() {
        val profile = mapper.transform(resume)
        whenever(endPoint.attemptGetExperience()).thenReturn(Single.just(resume))
        presenter.attemptGetResume(true)
        val captor : KArgumentCaptor<Profile> = argumentCaptor()
        verify(view, times(1)).displayProfile(captor.capture())
        assertEquals(captor.firstValue, profile)
    }
}