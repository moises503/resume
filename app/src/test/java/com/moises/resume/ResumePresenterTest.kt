package com.moises.resume

import com.moises.data.model.Profile
import com.moises.data.usecase.ResumeUseCase
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumePresenterImpl
import com.moises.presentation.resume.ResumeView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class ResumePresenterTest {

    lateinit var resumeUseCase: ResumeUseCase
    lateinit var resumeView : ResumeView
    lateinit var resumePresenter: ResumePresenter
    lateinit var profile : Profile

    @Before
    fun setup() {
        resumeUseCase = Mockito.mock(ResumeUseCase::class.java)
        resumeView = Mockito.mock(ResumeView::class.java)
        profile = Mockito.mock(Profile::class.java)
        resumePresenter = ResumePresenterImpl(resumeUseCase, resumeView)
    }

    @Test
    fun whenAttemptGetResumeAndIsRightDisplayProfileInView() {
        resumePresenter.attemptGetResume()
        verify(resumeView, times(1)).showLoading()
        verify(resumeView, times(1)).hideViews()
        verify(resumeView, times(1)).hideLoading()
        verify(resumeView, times(1)).showViews()
        verify(resumeView, times(1)).displayProfile(profile)
        verify(resumeView, never()).showError("This is an error")
    }
}
