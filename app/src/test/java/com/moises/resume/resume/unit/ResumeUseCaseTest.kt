package com.moises.resume.resume.unit

import com.moises.domain.core.Observer
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.resume.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit

class ResumeUseCaseTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    lateinit var resumeUseCase: ResumeUseCase
    lateinit var resumeObserver : Observer<Profile>
    lateinit var profile : Profile

    @Before
    fun setup() {
        resumeUseCase = mock()
        resumeObserver = mock()
        profile = Profile("", "", "", "", "",
            "", "", emptyList())
    }

    @Test
    fun whenExecuteUseCaseAlwaysReturnProfile() {
        whenever(resumeUseCase.execute(Unit, resumeObserver)).then {
            resumeObserver.onSuccess(profile)
        }
        resumeUseCase.execute(Unit, resumeObserver)
        verify(resumeObserver, times(1)).onSuccess(profile)
        verify(resumeObserver, never()).onError(any())
    }

    @Test(expected = Exception::class)
    fun whenExecuteUseCaseAndSendNullParamsThrowAnException(){
        doAnswer { throw Exception("This is an error") }
            .`when`(resumeUseCase).execute(null, resumeObserver)
        resumeUseCase.execute(null, resumeObserver)
    }
}