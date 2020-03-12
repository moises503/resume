package com.moises.domain.skillset.usecase

import com.moises.domain.core.SingleUseCase
import com.moises.domain.core.exceptions.NullParametersException
import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import com.moises.domain.skillset.model.Skillset
import com.moises.domain.skillset.repository.SkillsetRepository
import io.reactivex.Single

class SkillsetUseCase(private val skillsetRepository: SkillsetRepository, jobScheduler: JobScheduler, uiScheduler: UIScheduler)
    : SingleUseCase<SkillsetUseCase.SkillsetParams, Skillset>(uiScheduler, jobScheduler) {

    override fun buildUseCase(params: SkillsetParams?): Single<Skillset> {
        return params?.let {
            skillsetRepository.retrieveSkillset(params.hasInternetConnection)
        } ?: Single.error(NullParametersException("parameters can not be null"))
    }

    data class SkillsetParams(val hasInternetConnection : Boolean)
}