package com.moises.resume

import com.moises.data.model.Profile
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumeView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class ResumePresenterTest {

    @Mock
    lateinit var resumePresenter: ResumePresenter
    @Mock
    lateinit var resumeView: ResumeView
    @Mock
    lateinit var profile : Profile

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
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
