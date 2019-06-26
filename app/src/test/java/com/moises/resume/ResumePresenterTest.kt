package com.moises.resume

import com.moises.data.core.executor.JobThread
import com.moises.data.model.Profile
import com.moises.data.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.presentation.resume.ResumeView
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit


class ResumePresenterTest {

    lateinit var resumePresenter: ResumePresenter
    lateinit var resumeUseCase: ResumeUseCase
    lateinit var resumeView: ResumeView
    lateinit var profile : Profile

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setup() {
        profile = mock()
        resumeView = mock()
        resumeUseCase = ResumeUseCase(mock(), UIThread(), JobThread())
        resumePresenter = ResumePresenterImpl(resumeUseCase, resumeView)
    }

    @Test
    fun whenAttemptGetResumeAndIsRightNeverShowError() {
        resumePresenter.attemptGetResume()
        verify(resumeView, never()).showError("This is an error")
    }
}
