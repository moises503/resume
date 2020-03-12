package com.moises.presentation.skillset

import com.moises.domain.core.Observer
import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.usecase.SkillsetUseCase
import com.moises.presentation.core.BasePresenter

class SkillsetPresenterImpl(
    private val skillsetUseCase: SkillsetUseCase,
    private val skillsetView: SkillsetView
) : SkillsetPresenter, BasePresenter() {

    override fun retrieveSkillset(hasInternetConnection : Boolean) {
        skillsetView.showLoader()
        skillsetView.hideUIElements()
        skillsetUseCase.execute(SkillsetUseCase.SkillsetParams(hasInternetConnection), SkillsetObserver())
    }

    override fun onStop() {
        skillsetUseCase.dispose()
    }

    private inner class SkillsetObserver : Observer<Skillset>() {
        override fun onSuccess(t: Skillset) {
            displayUIElements()
            skillsetView.setSkillset(t)
        }

        override fun onError(e: Throwable) {
            displayUIElements()
            skillsetView.showError(e.message ?: "it can not retrieve skillset")
        }

        private fun displayUIElements() {
            skillsetView.hideLoader()
            skillsetView.showUIElements()
        }
    }
}