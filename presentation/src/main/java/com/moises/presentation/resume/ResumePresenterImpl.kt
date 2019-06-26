package com.moises.presentation.resume

import com.moises.domain.Observer
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.presentation.core.BasePresenter

class ResumePresenterImpl(private val resumeUseCase: ResumeUseCase, private val resumeView: ResumeView)
    : BasePresenter(), ResumePresenter {

    override fun attemptGetResume() {
        resumeView.hideViews()
        resumeView.showLoading()
        resumeUseCase.execute(Unit, ResumeObserver())
    }

    override fun onStop() {
        resumeUseCase.dispose()
    }

    private inner class ResumeObserver : Observer<Profile>() {
        override fun onSuccess(t: Profile) {
            resumeView.hideLoading()
            resumeView.showViews()
            resumeView.displayProfile(t)
        }

        override fun onError(e: Throwable) {
            resumeView.hideLoading()
            resumeView.showError(e.message ?: "")
        }
    }
}