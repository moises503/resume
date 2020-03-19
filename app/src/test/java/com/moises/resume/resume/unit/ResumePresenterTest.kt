package com.moises.resume.resume.unit

import com.moises.domain.resume.model.Profile
import com.moises.presentation.resume.ResumePresenter
import com.moises.presentation.resume.ResumeView
import com.moises.resume.RxImmediateSchedulerRule
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit


class ResumePresenterTest {

    lateinit var resumePresenter: ResumePresenter
    lateinit var resumeView : ResumeView
    lateinit var profile : Profile
    lateinit var errorMessage : String
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setup() {
        resumePresenter = mock()
        resumeView = mock()
        profile = Profile("", "","","","","","", emptyList())
        errorMessage = "This is an error"
    }

    @Test
    fun whenAttemptGetResumeAndIsRightDisplayProfileInView() {
        whenever(resumePresenter.attemptGetResume(true)).then {
            resumeView.displayProfile(profile)
        }
        resumePresenter.attemptGetResume(true)
        verify(resumeView, times(1)).displayProfile(profile)
        verify(resumeView, never()).showError(errorMessage)
    }

    @Test
    fun whenAttemptGetResumeAndIsWrongDisplayAnError() {
        whenever(resumePresenter.attemptGetResume(true)).then {
            resumeView.showError(errorMessage)
        }
        resumePresenter.attemptGetResume(true)
        verify(resumeView, never()).displayProfile(profile)
        verify(resumeView, times(1)).showError(errorMessage)
    }
}
