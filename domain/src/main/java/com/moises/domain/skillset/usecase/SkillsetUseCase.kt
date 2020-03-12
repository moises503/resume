package com.moises.domain.skillset.usecase

import com.moises.domain.core.ObservableUseCase
import com.moises.domain.core.exceptions.NullParametersException
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.repository.SkillsetRepository
import io.reactivex.Observable

class SkillsetUseCase(private val skillsetRepository: SkillsetRepository, jobScheduler: JobScheduler, uiScheduler: UIScheduler)
    : ObservableUseCase<SkillsetUseCase.SkillsetParams, Skillset>(uiScheduler, jobScheduler) {

    override fun buildUseCase(params: SkillsetParams?): Observable<Skillset> {
        return params?.let {
            skillsetRepository.retrieveSkillsetAsObservable(params.hasInternetConnection)
        } ?: Observable.error(NullParametersException("parameters can not be null"))
    }

    data class SkillsetParams(val hasInternetConnection : Boolean)
}