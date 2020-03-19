package com.moises.presentation.resume

import com.moises.domain.core.SingleObserver
import com.moises.domain.resume.model.Profile
import com.moises.domain.resume.usecase.ResumeUseCase
import com.moises.presentation.core.BasePresenter

class ResumePresenterImpl(private val resumeUseCase: ResumeUseCase, private val resumeView: ResumeView)
    : BasePresenter(), ResumePresenter {

    override fun attemptGetResume(hasInternetConnection : Boolean) {
        resumeView.hideViews()
        resumeView.showLoading()
        resumeUseCase.execute(ResumeUseCase.ResumeParams(hasInternetConnection), ResumeObserver())
    }

    override fun onStop() {
        resumeUseCase.dispose()
    }

    private inner class ResumeObserver : SingleObserver<Profile>() {
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