package com.moises.presentation.resume

import com.moises.data.model.Profile
import com.moises.domain.Observer
import com.moises.data.usecase.ResumeUseCase
import com.moises.presentation.BasePresenter

class ResumePresenterImpl(private val resumeUseCase: ResumeUseCase, private val resumeView: ResumeView)
    : BasePresenter(), ResumePresenter {

    override fun attemptGetResume() {
        resumeView.hideViews()
        resumeView.showLoading()
        super.addDisposable(resumeUseCase.execute(ResumeObserver(), Unit))
    }

    override fun onDestroy() {
        super.destroy()
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