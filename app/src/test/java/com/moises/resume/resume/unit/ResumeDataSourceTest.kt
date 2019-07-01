package com.moises.resume.resume.unit

import com.moises.data.resume.mapper.ResumeResponseToProfile
import com.moises.data.resume.model.Resume
import com.moises.domain.resume.datasource.ResumeDataSource
import com.moises.resume.RxImmediateSchedulerRule
import com.moises.resume.resume.faker.ResumeSeeder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class ResumeDataSourceTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var mapper : ResumeResponseToProfile
    lateinit var dataSource: ResumeDataSource
    lateinit var resume : Resume
    lateinit var profile : Single<com.moises.domain.resume.model.Profile>

    @Before
    fun setup() {
        resume = ResumeSeeder.makeFakeData()
        mapper = ResumeResponseToProfile()
        profile = Single.just(resume).map { mapper.transform(it) }
        dataSource = mock()
    }

    @Test()
    fun whenExecuteDataSourceTransformResponseToDomainEntity(){
        whenever(dataSource.getResume()).thenReturn(profile)
        val result = dataSource.getResume()
        assertEquals(result, profile)
    }
}