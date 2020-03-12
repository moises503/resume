package com.moises.presentation.skillset

import com.moises.domain.core.ObservableObserver
import com.moises.domain.core.SingleObserver
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

    private inner class SkillsetObserver : ObservableObserver<Skillset>() {

        override fun onComplete() {
            displayUIElements()
        }

        override fun onError(e: Throwable?) {
            skillsetView.showError(e?.message ?: "it can not retrieve skillset")
        }

        override fun onNext(value: Skillset) {
            skillsetView.setSkillset(value)
        }

        private fun displayUIElements() {
            skillsetView.hideLoader()
            skillsetView.showUIElements()
        }
    }
}