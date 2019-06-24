package com.moises.resume

import com.moises.data.model.Profile
import com.moises.data.usecase.ResumeUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
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
    lateinit var profile : Profile

    @Before
    fun setup() {
        resumeUseCase = mock()
    }

    @Test
    fun whenExecuteUseCaseAlwaysReturnProfile() {
        profile = Profile("", "","","", "", "", "", emptyList())
        whenever(resumeUseCase.buildUseCaseObservable(Unit)).thenReturn(Single.just(profile))
        val result = resumeUseCase.buildUseCaseObservable(Unit)
        val testObserver = TestObserver<Profile>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}